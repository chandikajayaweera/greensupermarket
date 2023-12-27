package com.greensupermarket.controller;

import com.greensupermarket.model.Cart;
import com.greensupermarket.model.OrderItem;
import com.greensupermarket.model.Customer;
import com.greensupermarket.model.Product;

import com.greensupermarket.service.CustomerService;
import com.greensupermarket.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    //Models
    private final OrderItem orderItem;
    private final Customer customer;

    //Services
    private final CustomerService customerService;
    private final ProductService productService;

    // Constructor
    public CartController() {

        //Models
        this.orderItem = new OrderItem();
        this.customer = new Customer();

        //Services
        this.customerService = new CustomerService();
        this.productService = new ProductService();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isLogged = authenticateSession(session, request, response);

        if (!isLogged) {
            response.sendRedirect("customer/login?action=logout");
            return;
        }

        if (request.getParameter("action") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Cart cart = getOrCreateCart(session);

        String action = request.getParameter("action");

        switch (action) {
            case "addtocart":
                addToCart(session, request, response);
                break;

            case "removefromcart":
                if (cart != null) {
                    cart.removeItem(Integer.parseInt(request.getParameter("productid")));
                }
                session.setAttribute("cart", cart.getOrderItems()); //List<OrderItem>
                session.setAttribute("total", cart.getTotalPrice());
                response.sendRedirect("cart.jsp");
                break;

            case "viewcart":
                session.setAttribute("cart", cart.getOrderItems()); //List<OrderItem>
                session.setAttribute("total", cart.getTotalPrice());
                response.sendRedirect("cart.jsp");
                break;

            case "checkout":
                Customer customer = (Customer) session.getAttribute("customer");
                session.setAttribute("orderitems", cart.getOrderItems());
                session.setAttribute("total", cart.getTotalPrice());
                response.sendRedirect("checkout.jsp");
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

        if (request.getParameter("action") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Cart cart = getOrCreateCart(session);

        String action = request.getParameter("action");

        switch (action) {
            case "test":
                break;

            default:
                response.sendRedirect("index.jsp");
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

    private Cart getOrCreateCart(HttpSession session) {
        Object cartObject = session.getAttribute("cart");

        if (cartObject == null) {
            Cart cart = new Cart();
            session.setAttribute("cart", cart);
            return cart;
        }

        if (cartObject instanceof Cart) {
            return (Cart) cartObject;

        } else if (cartObject instanceof ArrayList) {
            Cart cart = new Cart();
            cart.getOrderItems().addAll((ArrayList<OrderItem>) cartObject);
            session.setAttribute("cart", cart);
            return cart;
        } else {
            throw new RuntimeException("Unexpected object type for 'cart' in the session");
        }
    }

    private void addToCart(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productid"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Cart cart = getOrCreateCart(session);

        Product product = productService.getProductByID(productId);

        if (product != null) {
            // Check if the product is already in the cart
            OrderItem existingItem = findOrderItemInCart(cart, productId);

            if (existingItem != null) {

                if ((existingItem.getOrderItemQuantity() + quantity) > product.getProductStock()) {
                    throw new IllegalArgumentException("Quantity exceeds available stock");
                }

                existingItem.setOrderItemQuantity(existingItem.getOrderItemQuantity() + quantity);

            } else {

                OrderItem orderItem = new OrderItem();
                orderItem.setProductID(product.getProductID());
                orderItem.setProductName(product.getProductName());
                orderItem.setProductImageURL(product.getProductImageURL());
                orderItem.setOrderItemUnitPrice(product.getProductUnitPrice());
                orderItem.setOrderItemQuantity(quantity);
                cart.addItem(orderItem);
            }
        }

        String referringURL = request.getHeader("referer");
        response.sendRedirect(referringURL != null ? referringURL : "index.jsp");
        return;
    }

    private OrderItem findOrderItemInCart(Cart cart, int productID) {
        return cart.getOrderItems()
                .stream()
                .filter(item -> item.getProductID() == productID)
                .findFirst()
                .orElse(null);
    }

}
