package com.greensupermarket.controller;

import com.greensupermarket.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", urlPatterns = {"/backend/login"})
public class LoginController extends HttpServlet {
    
    private EmployeeService employeeService;
    
    public LoginController(){
        this.employeeService = new EmployeeService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (employeeService.authenticateEmployee(email, password)) {
            session.setAttribute("employee", employeeService.getEmployeeByEmail(email));
            response.sendRedirect("dashboard.jsp");
            return;
        }
        response.sendRedirect("login.jsp");        
    }

}
