package com.greensupermarket.controller;

import com.greensupermarket.service.EmployeeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/backend/login"})
public class EmployeeLoginController extends HttpServlet {

    private EmployeeService employeeService;

    public EmployeeLoginController() {
        this.employeeService = new EmployeeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if (request.getParameter("action") == null) {
            logout(session, response);
            return;
        }
        
        String action = request.getParameter("action");

        switch (action) {
            case "logout":
                logout(session, response);
                return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if (request.getParameter("action") == null) {
            logout(session, response);
            return;
        }
        
        String action = request.getParameter("action");

        switch (action) {
            case "logout":
                logout(session, response);
                return;
            case "login":
                login(session, request, response);
                return;
        }
        
    }

    private void logout(HttpSession session, HttpServletResponse response) throws ServletException, IOException {
        session.removeAttribute("employee");
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
    
    private void login(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (employeeService.authenticateEmployee(email, password)) {
            session.setAttribute("employee", employeeService.getEmployeeByEmail(email));
            response.sendRedirect("controller?action=categories");
            return;
        }
        response.sendRedirect("login.jsp");
    }

}
