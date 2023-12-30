package com.greensupermarket.controller;

//models
import com.greensupermarket.model.Customer;
import com.greensupermarket.model.Feedback;
import com.greensupermarket.model.CustomerOrder;
import com.greensupermarket.model.OrderItem;
import com.greensupermarket.model.ShippingDetails;
import com.greensupermarket.model.Product;

//services
import com.greensupermarket.service.CustomerService;
import com.greensupermarket.service.FeedbackService;
import com.greensupermarket.service.CustomerOrderService;
import com.greensupermarket.service.OrderItemService;
import com.greensupermarket.service.ShippingDetailsService;
import com.greensupermarket.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = {"/customer/controller"})
public class CustomerController extends HttpServlet {

    //model
    private final Customer customer;
    private final Feedback feedback;
    private final CustomerOrder customerOrder;
    private final OrderItem orderItem;
    private final ShippingDetails shippingDetails;

    //service
    private final CustomerService customerService;
    private final FeedbackService feedbackService;
    private final CustomerOrderService customerOrderService;
    private final OrderItemService orderItemService;
    private final ShippingDetailsService shippingDetailsService;
    private final ProductService productService;

    // Constructor
    public CustomerController() {
        //model
        this.customer = new Customer();
        this.feedback = new Feedback();
        this.customerOrder = new CustomerOrder();
        this.orderItem = new OrderItem();
        this.shippingDetails = new ShippingDetails();

        //service
        this.customerService = new CustomerService();
        this.feedbackService = new FeedbackService();
        this.customerOrderService = new CustomerOrderService();
        this.orderItemService = new OrderItemService();
        this.shippingDetailsService = new ShippingDetailsService();
        this.productService = new ProductService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean isLogged = authenticateSession(session, request, response);

        if (isLogged == false) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (request.getParameter("action") == null) {
            response.sendRedirect("controller?action=orders");
        }

        String action = request.getParameter("action");

        switch (action) {
            case "feedback":
                response.sendRedirect("feedback.jsp");
                return;
            case "profile":
                response.sendRedirect("profile.jsp");
                return;
            case "orders":
                orders(session, request, response);
                return;
            case "invoice":
                invoice(session, request, response);
                return;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean isLogged = authenticateSession(session, request, response);

        if (isLogged == false) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (request.getParameter("action") == null) {
            response.sendRedirect("controller?action=orders");
        }

        String action = request.getParameter("action");

        switch (action) {
            case "feedback":
                feedback(session, request, response);
                break;
            case "profile":
                profile(session, request, response);
                break;
            case "orders":
                orders(session, request, response);
                return;
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

    private void feedback(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LocalDateTime feedbackDateTime = LocalDateTime.now();
        Date feedbackDate = Date.from(feedbackDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Customer customer = (Customer) session.getAttribute("customer");
        feedback.setFeedbackDate(feedbackDate);
        feedback.setCustomerID(customer.getCustomerID());
        feedback.setFeedbackRating(Integer.parseInt(request.getParameter("rate")));
        feedback.setFeedbackMessage(request.getParameter("message"));
        feedbackService.addFeedback(feedback);
        response.sendRedirect("feedback.jsp");

        return;
    }

    private void profile(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("query") == null) {
            response.sendRedirect("profile.jsp");
            return;
        }

        String query = request.getParameter("query");

        switch (query) {
            case "update":
                customer.setCustomerID(Integer.parseInt(request.getParameter("customerid")));
                customer.setCustomerFname(request.getParameter("fname"));
                customer.setCustomerLname(request.getParameter("lname"));
                customer.setCustomerEmail(request.getParameter("email"));
                customer.setCustomerPnumber(request.getParameter("pnumber"));
                customerService.updateCustomer(customer);
                session.removeAttribute("customer");
                session.setAttribute("customer", customerService.getCustomerByID(Integer.parseInt(request.getParameter("customerid"))));
                response.sendRedirect("profile.jsp");
                return;

            case "updatepassword":
                customer.setCustomerID(Integer.parseInt(request.getParameter("customerid")));
                customer.setCustomerPasswordHash(request.getParameter("password"));
                customerService.updateCustomerpassword(customer);
                response.sendRedirect("profile.jsp");
                return;

        }
    }

    private void orders(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Customer customer = (Customer) session.getAttribute("customer");

        List<CustomerOrder> customerOrder = customerOrderService.getAllCustomerOrdersByCustomerID(customer.getCustomerID());

        session.setAttribute("customerorder", customerOrder);

        response.sendRedirect("orders.jsp");
    }

    private void invoice(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String orderidparam = request.getParameter("orderid");

        if (orderidparam == null) {
            response.sendRedirect("controller?action=orders");
            return;
        }

        int orderid = Integer.parseInt(orderidparam);

        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(orderid);
        session.setAttribute("customerorder", customerOrder);

        List<ShippingDetails> shippingDetails = shippingDetailsService.getShippingDetailsByCustomerOrderID(orderid);
        session.setAttribute("shippingdetails", shippingDetails);

        List<OrderItem> orderItems = orderItemService.getAllOrderItemsByOrderId(orderid);
        session.setAttribute("orderitems", orderItems);

        for (OrderItem orderItem : orderItems) {
            Product product = new Product();
            product = productService.getProductByID(orderItem.getProductID());
            orderItem.setProductName(product.getProductName());
            orderItem.setProductImageURL(product.getProductImageURL());
        }
        
        response.sendRedirect("invoice.jsp");
        return;
    }
}
