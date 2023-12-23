package com.greensupermarket.controller;

import com.greensupermarket.service.CustomerService;
import com.greensupermarket.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CustomerLoginController", urlPatterns = {"/customer/login"})
public class CustomerLoginController extends HttpServlet {

    private CustomerService customerService;
    private Customer customer;

    public CustomerLoginController() {
        this.customerService = new CustomerService();
        this.customer = new Customer();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        switch (action) {
            case "logout":
                logout(session, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                login(request, response, session);
                break;
            case "signup":
                signup(request, response, session);
                break;
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String email = request.getParameter("customeremail");
        String password = request.getParameter("customerpassword");

        if (customerService.authenticateCustomer(email, password)) {
            session.setAttribute("customer", customerService.getCustomerByEmail(email));
            response.sendRedirect("dashboard.jsp");
            return;
        }
        response.sendRedirect("login.jsp");
    }

    private void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("employee");
        session.invalidate();
        response.sendRedirect("login.jsp");
        return;
    }

    private void signup(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        customer.setCustomerFname(request.getParameter("customerfname"));
        customer.setCustomerLname(request.getParameter("customerlname"));
        customer.setCustomerEmail(request.getParameter("customeremail"));
        customer.setCustomerPnumber(request.getParameter("customerpnumber"));
        customer.setCustomerPasswordHash(request.getParameter("customerpassword"));
        customerService.addCustomer(customer);
        session.setAttribute("customer", customerService.getCustomerByEmail(request.getParameter("customeremail")));
        response.sendRedirect("dashboard.jsp");
        return;
    }

}
