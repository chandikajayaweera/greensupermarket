package com.greensupermarket.controller;

import com.greensupermarket.service.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "testing", urlPatterns = {"/testing"})
public class testing extends HttpServlet {
    
    final CustomerService customerService;
    
    public testing(){
        this.customerService = new CustomerService();
    }
    

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println(customerService.getCustomerByEmail("chandika@test.com"));
    }


}
