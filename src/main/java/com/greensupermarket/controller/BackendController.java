package com.greensupermarket.controller;

import com.greensupermarket.service.*;
import com.greensupermarket.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "BackendController", urlPatterns = {"/backend/controller"})
public class BackendController extends HttpServlet {

    // Services
    private EmployeeService employeeService;
    private UnitService unitService;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;
    private CustomerService customerService;
    private ProductService productService;
    private FeedbackService feedbackService;

    // Models
    private Employee employee;
    private Unit unit;
    private Category category;
    private SubCategory subCategory;
    private Customer customer;
    private Product product;

    // Constructor
    public BackendController() {

        // Services
        this.employeeService = new EmployeeService();
        this.unitService = new UnitService();
        this.categoryService = new CategoryService();
        this.subCategoryService = new SubCategoryService();
        this.customerService = new CustomerService();
        this.productService = new ProductService();
        this.feedbackService = new FeedbackService();

        // Models
        this.employee = new Employee();
        this.unit = new Unit();
        this.category = new Category();
        this.subCategory = new SubCategory();
        this.customer = new Customer();
        this.product = new Product();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        boolean isLogged = authenticateSession(session, request, response);

        if (isLogged == false) {
            response.sendRedirect("login?action=logout");
            return;
        }
        if (request.getParameter("action") == null) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        String action = request.getParameter("action");
        
        switch (action) {
            case "units":
                session.setAttribute("units", unitService.getAllUnits());
                response.sendRedirect("units.jsp");
                return;
            case "categories":
                session.setAttribute("categories", categoryService.getAllCategories());
                response.sendRedirect("categories.jsp");
                return;
            case "subcategories":
                session.setAttribute("categories", categoryService.getAllCategories());
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                response.sendRedirect("subcategories.jsp");
                return;
            case "customers":
                session.setAttribute("customers", customerService.getAllCustomers());
                response.sendRedirect("customers.jsp");
                return;
            case "products":
                session.setAttribute("units", unitService.getAllUnits());
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                session.setAttribute("products", productService.getAllProducts());
                response.sendRedirect("products.jsp");
                return;
            case "customerfeedback":
                session.setAttribute("feedback", feedbackService.getAllFeedbacks());
                response.sendRedirect("feedback.jsp");
                return;
            case "employees":
                session.setAttribute("employees", employeeService.getAllEmployees());
                response.sendRedirect("employees.jsp");
                return;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        boolean isLogged = authenticateSession(session, request, response);

        if (isLogged == false) {
            response.sendRedirect("login?action=logout");
            return;
        }
        if (request.getParameter("action") == null) {
            response.sendRedirect("dashboard.jsp");
        }

        String action = request.getParameter("action");

        switch (action) {
            case "units":
                units(session, request, response);
                return;
            case "categories":
                categories(session, request, response);
                return;
            case "subcategories":
                subCategories(session, request, response);
                return;
            case "customers":
                customers(session, request, response);
                return;
            case "products":
                products(session, request, response);
                return;
            case "feedback":
                /* Empty */
                return;
            case "employees":
                employees(session, request, response);
                return;
        }
    }

    private boolean authenticateSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null || employee.getEmployeeEmail() == null) {
            return false;
        }
        if (employeeService.getEmployeeByEmail(employee.getEmployeeEmail()) == null) {
            return false;
        }
        return true;
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

    private void categories(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session.setAttribute("categories", categoryService.getAllCategories());

        if (request.getParameter("query") == null) {
            response.sendRedirect("categories.jsp");
            return;
        }

        String query = request.getParameter("query");

        switch (query) {
            case "add":
                Part categoryImage = request.getPart("categoryimage");
                category.setCategoryName(request.getParameter("categoryname"));
                categoryService.addCategory(category, categoryImage, request);
                session.setAttribute("categories", categoryService.getAllCategories());
                response.sendRedirect("categories.jsp");
                return;

            case "update":
                categoryImage = request.getPart("categoryimage");
                category.setCategoryName(request.getParameter("categoryname"));
                categoryService.updateCategory(category, categoryImage, request);
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

    private void subCategories(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.setAttribute("subcategories", subCategoryService.getAllSubCategories());

        if (request.getParameter("query") == null) {
            response.sendRedirect("subcategories.jsp");
            return;
        }

        String query = request.getParameter("query");

        switch (query) {
            case "add":
                subCategory.setCategoryName(request.getParameter("categoryname"));
                subCategory.setSubCategoryName(request.getParameter("subcategoryname"));
                subCategoryService.addSubCategory(subCategory);
                session.setAttribute("categories", categoryService.getAllCategories());
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                response.sendRedirect("subcategories.jsp");
                return;

            case "delete":
                subCategoryService.deleteSubCategory(request.getParameter("subcategoryname"));
                session.setAttribute("categories", categoryService.getAllCategories());
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                response.sendRedirect("subcategories.jsp");
                return;
        }
    }

    private void customers(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.setAttribute("customers", customerService.getAllCustomers());

        if (request.getParameter("query") == null) {
            response.sendRedirect("customers.jsp");
            return;
        }

        String query = request.getParameter("query");

        switch (query) {
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

    private void products(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session.setAttribute("units", unitService.getAllUnits());
        session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
        session.setAttribute("products", productService.getAllProducts());

        if (request.getParameter("query") == null) {
            response.sendRedirect("products.jsp");
            return;
        }

        String query = request.getParameter("query");

        switch (query) {
            case "add":
                Part productImage = request.getPart("productimage");
                product.setProductName(request.getParameter("productname"));
                product.setProductSKU(request.getParameter("productsku"));
                product.setBrandName(request.getParameter("brandname"));
                product.setUnitName(request.getParameter("unitname"));
                product.setSubCategoryName(request.getParameter("subcategoryname"));
                product.setProductDescription(request.getParameter("productdescription"));
                product.setProductUnitPrice(Double.parseDouble(request.getParameter("productunitprice")));
                product.setProductStock(Integer.parseInt(request.getParameter("productstock")));
                productService.addProduct(product, productImage, request);
                session.setAttribute("units", unitService.getAllUnits());
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                session.setAttribute("products", productService.getAllProducts());
                response.sendRedirect("products.jsp");
                return;

            case "delete":
                productService.deleteProduct(Integer.parseInt(request.getParameter("productid")));
                session.setAttribute("units", unitService.getAllUnits());
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                session.setAttribute("products", productService.getAllProducts());
                response.sendRedirect("products.jsp");
                return;
        }
    }

    private void employees(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session.setAttribute("employees", employeeService.getAllEmployees());
        
        if (request.getParameter("query") == null) {
            response.sendRedirect("employees.jsp");
            return;
        }
        
        String query = request.getParameter("query");
        
        switch (query) {
            case "add":
                employee.setRoleName(request.getParameter("employeerole"));
                employee.setEmployeeFname(request.getParameter("employeefname"));
                employee.setEmployeeLname(request.getParameter("employeelname"));
                employee.setEmployeeEmail(request.getParameter("employeeemail"));
                employee.setEmployeePassword(request.getParameter("employeepassword"));
                employeeService.addEmployee(employee);
                session.setAttribute("employees", employeeService.getAllEmployees());
                response.sendRedirect("employees.jsp");
                return;
            
            case "update":
                employee.setEmployeeID(Integer.parseInt(request.getParameter("employeeid")));
                employee.setRoleName(request.getParameter("employeerole"));
                employee.setEmployeeFname(request.getParameter("employeefname"));
                employee.setEmployeeLname(request.getParameter("employeelname"));
                employee.setEmployeeEmail(request.getParameter("employeeemail"));
                employee.setEmployeePassword(request.getParameter("employeepassword"));
                employeeService.updateEmployee(employee);
                session.setAttribute("employees", employeeService.getAllEmployees());
                response.sendRedirect("employees.jsp");
                return;
                
                
            case "delete":
                employeeService.deleteEmployee(Integer.parseInt(request.getParameter("employeeid")));
                session.setAttribute("employees", employeeService.getAllEmployees());
                response.sendRedirect("employees.jsp");
                return;
        }
        
    }
}
