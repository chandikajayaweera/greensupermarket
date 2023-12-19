package com.greensupermarket.controller;

import com.greensupermarket.service.*;
import com.greensupermarket.model.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BackendController", urlPatterns = {"/backend/controller"})
public class BackendController extends HttpServlet {

    // Services
    private EmployeeService employeeService;
    private UnitService unitService;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;
    private CustomerService customerService;

    // Models
    private Unit unit;
    private Category category;
    private Employee employee;
    private SubCategory subCategory;
    private Customer customer;
    

    // Constructor
    public BackendController() {
        
        // Services
        this.employeeService = new EmployeeService();
        this.unitService = new UnitService();
        this.categoryService = new CategoryService();
        this.subCategoryService = new SubCategoryService();
        this.customerService = new CustomerService();
        
        // Models
        this.unit = new Unit();
        this.employee = new Employee();
        this.category = new Category();
        this.subCategory = new SubCategory();
        this.customer = new Customer();

    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        boolean isLogged = authenticateSession(session, request, response);

        if(isLogged == false){
            response.sendRedirect("login.jsp");
            return;           
        }
        if (request.getParameter("action") == null){
            response.sendRedirect("dashboard.jsp");
            return;
        }

        String action = request.getParameter("action");

        switch (action) {
            case "logout":
                logout(session, response);
                break;
            case "units":
                session.setAttribute("units", unitService.getAllUnits());
                response.sendRedirect("units.jsp");
                return;
            case "categories":
                session.setAttribute("categories", categoryService.getAllCategories());
                response.sendRedirect("categories.jsp");
                return;
            case "subcategories":
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                response.sendRedirect("subcategories.jsp");
                return;
            case "customers":
                session.setAttribute("customers", customerService.getAllCustomers());
                response.sendRedirect("customers.jsp");
                return;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        
        boolean isLogged = authenticateSession(session, request, response);

        if(isLogged == false){
            response.sendRedirect("login.jsp");
            return;           
        }
        if (request.getParameter("action") == null){
            response.sendRedirect("dashboard.jsp");            
        }
          
        String action = request.getParameter("action");

        switch (action) {
            case "units":
                units(session, request, response);
                break;
            case "categories":
                categories(session, request, response);
                break;
            case "subcategories":
                subCategories(session, request, response);
                break;
            case "customers":
                customers(session, request, response);
                break;
        }
    }
    
        
    private boolean authenticateSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Employee employee = (Employee) session.getAttribute("employee");
        
        if(employee == null || employee.getEmployeeEmail() == null){
            return false;            
        }
        if(employeeService.getEmployeeByEmail(employee.getEmployeeEmail()) == null){ 
            return false;
        }
        return true;
    }
    

    private void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("employee");
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    private void units(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.setAttribute("units", unitService.getAllUnits());

        if (request.getParameter("query") == null) {
            response.sendRedirect("units.jsp");
            return;
        }
        String query = request.getParameter("query");

        switch (query) {
            case "delete":
                unitService.deleteUnit(request.getParameter("unitname"));
                session.setAttribute("units", unitService.getAllUnits());
                response.sendRedirect("units.jsp");
                return;

            case "add":
                unit.setUnitName(request.getParameter("unitname"));
                unit.setUnitAbbreviation(request.getParameter("unitabbreviation"));
                unitService.addUnit(unit);
                session.setAttribute("units", unitService.getAllUnits());
                response.sendRedirect("units.jsp");
                return;
        }
    }

    private void categories(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.setAttribute("categories", categoryService.getAllCategories());

        if (request.getParameter("query") == null) {
            response.sendRedirect("categories.jsp");
            return;
        }

        String query = request.getParameter("query");

        switch (query) {
            case "add":
                /* Need to edit*/
                category.setCategoryName(request.getParameter("categoryname"));
                category.setCategoryImageURL(request.getParameter("categoryimageurl"));
                categoryService.addCategory(category);
                session.setAttribute("categories", categoryService.getAllCategories());
                response.sendRedirect("categories.jsp");
                return;
                
            case "update":
                /* Need to edit*/
                category.setCategoryName(request.getParameter("categoryname"));
                category.setCategoryImageURL(request.getParameter("categoryimageurl"));
                categoryService.updateCategory(category);
                session.setAttribute("categories", categoryService.getAllCategories());
                response.sendRedirect("categories.jsp");
                return;                
                
            case "delete":
                categoryService.deleteCategory(request.getParameter("categoryname"));
                session.setAttribute("categories", categoryService.getAllCategories());
                response.sendRedirect("categories.jsp");
                return;
        }
    }
    
    private void subCategories(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException{
        session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
        
        if (request.getParameter("query") == null) {
            response.sendRedirect("subcategories.jsp");
            return;
        }
        
        String query = request.getParameter("query");
        
        switch(query){
            case "add":
                subCategory.setCategoryName(request.getParameter("categoryname"));
                subCategory.setSubCategoryName(request.getParameter("subcategoryname"));
                subCategoryService.addSubCategory(subCategory);
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                response.sendRedirect("subcategories.jsp");
                return;              
                
            case "delete":
                subCategoryService.deleteSubCategory(request.getParameter("subcategoryname"));
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                response.sendRedirect("subcategories.jsp");
                return;                
        }
    }
    
    private void customers(HttpSession session, HttpServletRequest request, HttpServletResponse response)throws IOException{
        session.setAttribute("customers", customerService.getAllCustomers());
        
        if (request.getParameter("query") == null) {
            response.sendRedirect("customers.jsp");
            return;
        }
        
        String query = request.getParameter("query");
        
        switch(query){
            case "add":
                customer.setCustomerFname(request.getParameter("customerfname"));
                customer.setCustomerLname(request.getParameter("customerlname"));
                customer.setCustomerEmail(request.getParameter("customeremail"));
                customer.setCustomerPnumber(request.getParameter("customerpnumber"));
                customer.setCustomerPasswordHash(request.getParameter("customerpassword"));
                customerService.addCustomer(customer);
                session.setAttribute("customers", customerService.getAllCustomers());
                response.sendRedirect("customers.jsp");
                return;
            
            case "update":
                return;
                
            case "delete":
                customerService.deleteCustomer(Integer.parseInt(request.getParameter("customerid")));
                session.setAttribute("customers", customerService.getAllCustomers());
                response.sendRedirect("customers.jsp");
                return;                
        }
    }
}
