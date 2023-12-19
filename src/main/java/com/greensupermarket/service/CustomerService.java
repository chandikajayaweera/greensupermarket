package com.greensupermarket.service;

import com.greensupermarket.dao.CustomerDAO;
import com.greensupermarket.model.Customer;
import com.greensupermarket.util.PasswordHasher;
import com.greensupermarket.util.PasswordVerifier;
import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDao;
    private final PasswordHasher passwordHasher;
    private final PasswordVerifier passwordVerifier;
    
    public CustomerService(){
        this.customerDao = new CustomerDAO();
        this.passwordHasher = new PasswordHasher();
        this.passwordVerifier = new PasswordVerifier();        
    }
    
    // Add new customer
    public boolean addCustomer(Customer customer){
        if(customerDao.getCustomerByEmail(customer.getCustomerEmail()) == null && customerDao.getCustomerByPnumber(customer.getCustomerPnumber()) == null){
            customer.setCustomerPasswordHash(passwordHasher.hashPassword(customer.getCustomerPasswordHash()));
            return customerDao.addCustomer(customer);            
        }
        return false;
    }
    
    // update customer
    public boolean updateCustomer(Customer customer){
        if(customerDao.getCustomerByEmail(customer.getCustomerEmail()) != null){
            customer.setCustomerPasswordHash(passwordHasher.hashPassword(customer.getCustomerPasswordHash()));
            return customerDao.updateCustomer(customer);
        }
        return false;
    }
    
    // Delete customer
    public boolean deleteCustomer(int customerID){
        if(customerDao.getCustomerById(customerID) != null){
            return customerDao.deleteCustomer(customerID);
        }
        return false;
    }
    
    // Get all customers
    public List<Customer> getAllCustomers(){
        return customerDao.getAllCustomers();
    }
    
    // Authenticate customer
    public boolean authenticateCustomer(String customerEmail, String password){
        if(passwordVerifier.verifyPassword(password, customerDao.getPasswordByEmail(customerEmail))){
            return true;
        }
        return false;
    }
    
    // Get customer by email
    public Customer getCustomerByEmail(String customerEmail){
        return customerDao.getCustomerByEmail(customerEmail);
    }
}
