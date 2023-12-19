package com.greensupermarket.service;

import com.greensupermarket.dao.EmployeeDAO;
import com.greensupermarket.model.Employee;
import com.greensupermarket.util.PasswordHasher;
import com.greensupermarket.util.PasswordVerifier;

import java.util.List;

public class EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final PasswordHasher passwordHasher;
    private final PasswordVerifier passwordVerifier;
    
    // Contructor
    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
        this.passwordHasher = new PasswordHasher();
        this.passwordVerifier = new PasswordVerifier();
    }

    // Add a new employee
    public boolean addEmployee(Employee employee) {
        employee.setEmployeePassword(passwordHasher.hashPassword(employee.getEmployeePassword()));
        return employeeDAO.addEmployee(employee);
    }

    // Retrieve employee by ID
    public Employee getEmployeeById(int employeeID) {
        return employeeDAO.getEmployeeById(employeeID);
    }

    // Update employee
    public boolean updateEmployee(Employee employee) {
        employee.setEmployeePassword(passwordHasher.hashPassword(employee.getEmployeePassword()));
        return employeeDAO.updateEmployee(employee);
    }

    // Delete employee by ID
    public boolean deleteEmployee(int employeeID) {
        return employeeDAO.deleteEmployee(employeeID);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
    
    // Authenticate employee logins
    public boolean authenticateEmployee(String employeeEmail, String password){
        if(passwordVerifier.verifyPassword(password, employeeDAO.getPasswordByEmail(employeeEmail))){
            return true;
        }
        return false;
    }
    
    // Get Employee by email
    public Employee getEmployeeByEmail(String employeeEmail){
        return employeeDAO.getEmployeeByEmail(employeeEmail);
    }
    
    
    
}
