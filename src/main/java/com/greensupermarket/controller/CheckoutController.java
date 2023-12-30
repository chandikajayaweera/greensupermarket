package com.greensupermarket.controller;

import com.greensupermarket.model.Cart;
import com.greensupermarket.model.Customer;
import com.greensupermarket.model.CustomerOrder;
import com.greensupermarket.model.OrderItem;
import com.greensupermarket.model.Product;
import com.greensupermarket.model.ShippingDetails;

import com.greensupermarket.service.CustomerService;
import com.greensupermarket.service.CustomerOrderService;
import com.greensupermarket.service.ShippingDetailsService;
import com.greensupermarket.service.OrderItemService;
import com.greensupermarket.service.ProductService;
import com.greensupermarket.service.EmailService;

import com.greensupermarket.util.PaymentManager;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.mail.MessagingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    private final PaymentManager paymentManager;
    private final CustomerOrder customerOrder;
    private final ShippingDetails shippingDetails;

    private final CustomerService customerService;
    private final ShippingDetailsService shippingDetailsService;
    private final CustomerOrderService customerOrderService;
    private final OrderItemService orderItemService;
    private final ProductService productService;

    public CheckoutController() {
        this.paymentManager = new PaymentManager();
        this.customerService = new CustomerService();
        this.customerOrder = new CustomerOrder();
        this.shippingDetails = new ShippingDetails();
        this.shippingDetailsService = new ShippingDetailsService();
        this.customerOrderService = new CustomerOrderService();
        this.orderItemService = new OrderItemService();
        this.productService = new ProductService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isLogged = authenticateSession(session, request, response);

        if (!isLogged) {
            response.sendRedirect("customer/login?action=logout");
            return;
        } else if (request.getParameter("status") == null) {
            response.sendRedirect("index.jsp");
        }

        String status = request.getParameter("status");

        switch (status) {
            case "success":
                success(session, request, response);
                return;

            case "failed":
                response.sendRedirect("failed.jsp");
                return;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isLogged = authenticateSession(session, request, response);

        if (!isLogged) {
            response.sendRedirect("customer/login?action=logout");
            return;
        }

        APIContext apiContext = paymentManager.getAPIContext();

        Payment payment = createPayment(request.getParameter("total"));
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/greensupermarket/checkout?status=failed");
        redirectUrls.setReturnUrl("http://localhost:8080/greensupermarket/checkout?status=success");
        payment.setRedirectUrls(redirectUrls);

        try {
            Payment createdPayment = payment.create(apiContext);

            // Retrieve the approval link from the createdPayment to redirect the user to PayPal
            String approvalLink = createdPayment.getLinks().stream()
                    .filter(link -> "approval_url".equals(link.getRel().toLowerCase()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No 'approval_url' found"))
                    .getHref();

            // Redirect the user to the PayPal approval link
            response.sendRedirect(approvalLink);

        } catch (PayPalRESTException e) {
            e.printStackTrace();
            // Handle the exception or redirect to an error page
            response.sendRedirect("error.jsp");
        }
    }

    private boolean authenticateSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null || customer.getCustomerEmail() == null) {
            return false;
        }
        if (customerService.getCustomerByEmail(customer.getCustomerEmail()) == null) {
            return false;
        }
        return true;
    }

    private Payment createPayment(String totalAmount) {
        Payment payment = new Payment();
        payment.setIntent("sale");

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        payment.setPayer(payer);

        Transaction transaction = new Transaction();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(totalAmount);
        transaction.setAmount(amount);

        payment.setTransactions(Collections.singletonList(transaction));

        return payment;
    }

    public void success(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Customer customer = (Customer) session.getAttribute("customer");
            Cart cart = (Cart) session.getAttribute("cart");

            LocalDateTime customerOrderDateTime = LocalDateTime.now();
            Date customerOrderDate = Date.from(customerOrderDateTime.atZone(ZoneId.systemDefault()).toInstant());

            Payment payment = paymentManager.getPaymentDetails(request.getParameter("paymentId"));
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            String recipientName = payerInfo.getFirstName() + " " + payerInfo.getLastName();
            Address address = payment.getPayer().getPayerInfo().getShippingAddress();

            customerOrder.setCustomerID(customer.getCustomerID());
            customerOrder.setPaymentID(request.getParameter("paymentId"));
            customerOrder.setCustomerOrderStatus("Processing");
            customerOrder.setCustomerOrderDate(customerOrderDate);
            customerOrderService.createCustomerOrder(customerOrder);

            int customerOrderID = customerOrderService.getCustomerOrderIDByPaymentID(request.getParameter("paymentId"));

            shippingDetails.setRecipientName(recipientName);
            shippingDetails.setLine1(address.getLine1());
            shippingDetails.setLine2(address.getLine2());
            shippingDetails.setCity(address.getCity());
            shippingDetails.setState(address.getState());
            shippingDetails.setCountryCode(address.getCountryCode());
            shippingDetails.setPostalCode(address.getPostalCode());
            shippingDetailsService.createShippingDetails(shippingDetails, customerOrderID);

            for (var orderitem : cart.getOrderItems()) {

                OrderItem orderItem = new OrderItem();

                orderItem.setCustomerOrderID(customerOrderID);
                orderItem.setProductID(orderitem.getProductID());
                orderItem.setOrderItemQuantity(orderitem.getOrderItemQuantity());
                orderItem.setOrderItemUnitPrice(orderitem.getOrderItemUnitPrice());
                orderItemService.addOrderItem(orderItem);

                Product product = productService.getProductByID(orderitem.getProductID());
                int stock = product.getProductStock();
                stock -= orderitem.getOrderItemQuantity();
                product.setProductStock(stock);
                productService.updateProductStock(product);
            }

            StringBuilder emailContent = new StringBuilder();
            emailContent.append("Your order has been placed.\n\n");

            for (var orderitem : cart.getOrderItems()) {
                Product product = productService.getProductByID(orderitem.getProductID());

                emailContent.append("Product: ").append(product.getProductName()).append("\n");
                emailContent.append("Quantity: ").append(orderitem.getOrderItemQuantity()).append("\n");
                emailContent.append("Unit Price: ").append(orderitem.getOrderItemUnitPrice()).append("\n");
                emailContent.append("\n\n");
            }

            emailContent.append("Thank you for shopping with us!");

            EmailService emailService = new EmailService();
            emailService.sendEmail(customer.getCustomerEmail(), "Your order has been placed", emailContent.toString());

            session.removeAttribute("cart");
            session.setAttribute("customerorderid", customerOrderID);
            response.sendRedirect("success.jsp");
            return;

        } catch (MessagingException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
