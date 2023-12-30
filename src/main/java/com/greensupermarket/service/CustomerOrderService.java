package com.greensupermarket.service;

import com.greensupermarket.dao.CustomerOrderDAO;
import com.greensupermarket.model.CustomerOrder;

import java.util.List;

public class CustomerOrderService {

    private final CustomerOrderDAO customerOrderDAO;

    public CustomerOrderService() {
        this.customerOrderDAO = new CustomerOrderDAO();
    }

    public boolean createCustomerOrder(CustomerOrder customerOrder) {
        // Perform any additional business logic, validation, etc. before creating the order
        return customerOrderDAO.createCustomerOrder(customerOrder);
    }

    public CustomerOrder getCustomerOrderById(int customerOrderID) {
        // Perform any additional business logic, validation, etc. before retrieving the order
        return customerOrderDAO.getCustomerOrderById(customerOrderID);
    }

    public boolean updateCustomerOrder(CustomerOrder customerOrder) {
        // Perform any additional business logic, validation, etc. before updating the order
        return customerOrderDAO.updateCustomerOrder(customerOrder);
    }
    
    public boolean updateCustomerOrderStatus(CustomerOrder customerOrder){
        return customerOrderDAO.updateCustomerOrderStatus(customerOrder);
    }

    public List<CustomerOrder> getAllCustomerOrders() {
        // Perform any additional business logic, validation, etc. before retrieving all orders
        return customerOrderDAO.getAllCustomerOrders();
    }
    
    public int getCustomerOrderIDByPaymentID(String paymentID){
        return customerOrderDAO.getCustomerOrderIDByPaymentID(paymentID);
    }
    
    public int getCustomerIDByOrderID(int orderID){
        return customerOrderDAO.getCustomerIDByOrderID(orderID);
    }
    
    public List<CustomerOrder> getAllCustomerOrdersByCustomerID(int customerID){
        return customerOrderDAO.getAllCustomerOrdersByCustomerID(customerID);
    }
}
