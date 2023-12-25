package com.greensupermarket.controller;

import com.greensupermarket.model.Cart;
import com.greensupermarket.model.Customer;
import com.greensupermarket.model.OrderItem;

import com.greensupermarket.service.CartService;
import com.greensupermarket.service.CustomerService;

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

    private final CartService cartService;
    private final CustomerService customerService;
    private final OrderItem orderItem;

    // Constructor
    public CartController() {
        this.cartService = new CartService();
        this.customerService = new CustomerService();
        this.orderItem = new OrderItem();
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
                int productId = Integer.parseInt(request.getParameter("productid"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                cartService.addToCart(cart, productId, quantity);

                String referringURL = request.getHeader("referer");
                response.sendRedirect(referringURL != null ? referringURL : "index.jsp");
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

}
