package com.greensupermarket.service;

import com.greensupermarket.dao.EmployeeDAO;
import com.greensupermarket.model.Employee;
import com.greensupermarket.util.PasswordHasher;
import com.greensupermarket.util.PasswordVerifier;

import java.util.List;

public class EmployeeService {

    private final EmployeeDAO employeeDao;
    private final PasswordHasher passwordHasher;
    private final PasswordVerifier passwordVerifier;

    // Contructor
    public EmployeeService() {
        this.employeeDao = new EmployeeDAO();
        this.passwordHasher = new PasswordHasher();
        this.passwordVerifier = new PasswordVerifier();
    }

    // Add a new employee
    public boolean addEmployee(Employee employee) {
        if (employeeDao.getEmployeeByEmail(employee.getEmployeeEmail()) == null) {
            employee.setEmployeePassword(passwordHasher.hashPassword(employee.getEmployeePassword()));
            return employeeDao.addEmployee(employee);
        }
        return false;
    }

    // Retrieve employee by ID
    public Employee getEmployeeById(int employeeID) {
        return employeeDao.getEmployeeById(employeeID);
    }

    // Update employee
    public boolean updateEmployee(Employee employee) {
        if (employeeDao.getEmployeeById(employee.getEmployeeID()) != null) {
            employee.setEmployeePassword(passwordHasher.hashPassword(employee.getEmployeePassword()));
            return employeeDao.updateEmployee(employee);
        }
        return false;
    }

    // Delete employee by ID
    public boolean deleteEmployee(int employeeID) {
        return employeeDao.deleteEmployee(employeeID);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    // Authenticate employee logins
    public boolean authenticateEmployee(String employeeEmail, String password) {
        if (passwordVerifier.verifyPassword(password, employeeDao.getPasswordByEmail(employeeEmail))) {
            return true;
        }
        return false;
    }

    // Get Employee by email
    public Employee getEmployeeByEmail(String employeeEmail) {
        return employeeDao.getEmployeeByEmail(employeeEmail);
    }

}
