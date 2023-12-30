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
import java.io.PrintWriter;
import java.util.List;

@MultipartConfig
@WebServlet(name = "BackendController", urlPatterns = {"/backend/controller"})
public class BackendController extends HttpServlet {

    // Services
    private EmployeeService employeeService;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;
    private CustomerService customerService;
    private ProductService productService;
    private FeedbackService feedbackService;
    private CustomerOrderService customerOrderService;
    private ShippingDetailsService shippingDetailsService;
    private OrderItemService orderItemService;

    // Models
    private Employee employee;
    private Category category;
    private SubCategory subCategory;
    private Customer customer;
    private Product product;

    // Constructor
    public BackendController() {

        // Services
        this.employeeService = new EmployeeService();
        this.categoryService = new CategoryService();
        this.subCategoryService = new SubCategoryService();
        this.customerService = new CustomerService();
        this.productService = new ProductService();
        this.feedbackService = new FeedbackService();
        this.customerOrderService = new CustomerOrderService();
        this.shippingDetailsService = new ShippingDetailsService();
        this.orderItemService = new OrderItemService();

        // Models
        this.employee = new Employee();
        this.category = new Category();
        this.subCategory = new SubCategory();
        this.customer = new Customer();
        this.product = new Product();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        boolean isLogged = authenticateSession(session, request, response);

        if (isLogged == false) {
            response.sendRedirect("login?action=logout");
            return;
        }
        if (request.getParameter("action") == null) {
            categories(session, request, response);
            return;
        }

        String action = request.getParameter("action");
        
        switch (action) {
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
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                session.setAttribute("products", productService.getAllProducts());
                response.sendRedirect("products.jsp");
                return;
                
            case "updateproduct":
                if(productService.getProductByID(Integer.parseInt(request.getParameter("productid"))) == null){
                    response.sendRedirect("controller?action=products");
                    return;
                }
                session.setAttribute("product", productService.getProductByID(Integer.parseInt(request.getParameter("productid"))));
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                response.sendRedirect("updateproduct.jsp");
                return;
                
            case "customerfeedback":
                session.setAttribute("feedback", feedbackService.getAllFeedbacks());
                response.sendRedirect("feedback.jsp");
                return;
            case "employees":
                session.setAttribute("employees", employeeService.getAllEmployees());
                response.sendRedirect("employees.jsp");
                return;
            
            case "updateemployee":
                if(employeeService.getEmployeeById(Integer.parseInt(request.getParameter("employeeid"))) == null){
                    response.sendRedirect("controller?action=employees");
                }
                session.setAttribute("employeeinfo", employeeService.getEmployeeById(Integer.parseInt(request.getParameter("employeeid"))));
                response.sendRedirect("updateemployee.jsp");
                return;
                
            case "customerorders":
                session.setAttribute("customerorders", customerOrderService.getAllCustomerOrders());
                response.sendRedirect("customerorders.jsp");
                return;
            case "invoice":
                invoice(session, request, response);
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
            categories(session, request, response);
            return;
        }

        String action = request.getParameter("action");

        switch (action) {
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
            case "customerorders":
                customerorders(session, request, response);
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

            case "delete":
                customerService.deleteCustomer(Integer.parseInt(request.getParameter("customerid")));
                session.setAttribute("customers", customerService.getAllCustomers());
                response.sendRedirect("customers.jsp");
                return;
        }
    }

    private void products(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                product.setSubCategoryName(request.getParameter("subcategoryname"));
                product.setProductDescription(request.getParameter("productdescription"));
                product.setProductUnitPrice(Double.parseDouble(request.getParameter("productunitprice")));
                product.setProductStock(Integer.parseInt(request.getParameter("productstock")));
                productService.addProduct(product, productImage, request);
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                session.setAttribute("products", productService.getAllProducts());
                response.sendRedirect("products.jsp");
                return;
                
            case "update":
                productImage = request.getPart("productimage");
                product.setProductID(Integer.parseInt(request.getParameter("productid")));
                product.setProductName(request.getParameter("productname"));
                product.setProductSKU(request.getParameter("productsku"));
                product.setBrandName(request.getParameter("brandname"));
                product.setSubCategoryName(request.getParameter("subcategoryname"));
                product.setProductDescription(request.getParameter("productdescription"));
                product.setProductUnitPrice(Double.parseDouble(request.getParameter("productunitprice")));
                productService.updateProduct(product, productImage, request);
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                session.setAttribute("products", productService.getAllProducts());
                response.sendRedirect("products.jsp");
                return;
                
            case "updatestock":
                product.setProductID(Integer.parseInt(request.getParameter("productid")));
                product.setProductStock(Integer.parseInt(request.getParameter("productstock")));
                productService.updateProductStock(product);
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                session.setAttribute("products", productService.getAllProducts());
                response.sendRedirect("products.jsp");
                return;
            
            case "updateunitprice":
                product.setProductID(Integer.parseInt(request.getParameter("productid")));
                product.setProductUnitPrice(Double.parseDouble(request.getParameter("unitprice")));
                productService.updateProductUnitPrice(product);
                session.setAttribute("subcategories", subCategoryService.getAllSubCategories());
                session.setAttribute("products", productService.getAllProducts());
                response.sendRedirect("products.jsp");
                return;
                
            case "delete":
                productService.deleteProduct(Integer.parseInt(request.getParameter("productid")));
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
                session.removeAttribute("employeeinfo");
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

    
    private void customerorders(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        
        if (request.getParameter("query") == null) {
            response.sendRedirect("controller?action=customerorders");
            return;
        }
        
        String query = request.getParameter("query");
        
        switch(query){
            case "orderstatus":
                if(request.getParameter("orderstatus") == null || request.getParameter("orderid") == null){
                    response.sendRedirect("controller?action=customerorders");
                }
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setCustomerOrderID(Integer.parseInt(request.getParameter("orderid")));
                customerOrder.setCustomerOrderStatus(request.getParameter("orderstatus"));
                customerOrderService.updateCustomerOrderStatus(customerOrder);
                response.sendRedirect("controller?action=customerorders");
                return;
        }
    }
    
    private void invoice(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String orderidparam = request.getParameter("orderid");
        
        if (orderidparam == null) {
            response.sendRedirect("controller?action=orders");
            return;
        }

        int orderid = Integer.parseInt(orderidparam);
        
        Customer customer = customerService.getCustomerByID(customerOrderService.getCustomerIDByOrderID(orderid));

        session.setAttribute("customerinfo", customer);
        
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
