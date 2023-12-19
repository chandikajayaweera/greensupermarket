package com.greensupermarket.dao;

import com.greensupermarket.model.Employee;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private final ConnectionManager connectionManager;

    public EmployeeDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new employee
    public boolean addEmployee(Employee employee) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Employee (RoleName, EmployeeID, EmployeeFname, EmployeeLname, EmployeeEmail, EmployeePasswordHash) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, employee.getRoleName());
            preparedStatement.setInt(2, employee.getEmployeeID());
            preparedStatement.setString(3, employee.getEmployeeFname());
            preparedStatement.setString(4, employee.getEmployeeLname());
            preparedStatement.setString(5, employee.getEmployeeEmail());
            preparedStatement.setString(6, employee.getEmployeePassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve employee by ID
    public Employee getEmployeeById(int employeeID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT RoleName, EmployeeID, EmployeeFname, EmployeeLname, EmployeeEmail FROM Employee WHERE EmployeeID = ?")) {

            preparedStatement.setInt(1, employeeID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToEmployee(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update employee
    public boolean updateEmployee(Employee employee) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Employee SET RoleName=?, EmployeeFname=?, EmployeeLname=?, EmployeeEmail=?, EmployeePasswordHash=? WHERE EmployeeID=?")) {

            preparedStatement.setString(1, employee.getRoleName());
            preparedStatement.setString(2, employee.getEmployeeFname());
            preparedStatement.setString(3, employee.getEmployeeLname());
            preparedStatement.setString(4, employee.getEmployeeEmail());
            preparedStatement.setString(5, employee.getEmployeePassword());
            preparedStatement.setInt(6, employee.getEmployeeID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete employee by ID
    public boolean deleteEmployee(int employeeID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Employee WHERE EmployeeID=?")) {

            preparedStatement.setInt(1, employeeID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT RoleName, EmployeeID, EmployeeFname, EmployeeLname, EmployeeEmail FROM Employee");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = mapResultSetToEmployee(resultSet);
                employeeList.add(employee);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return employeeList;
    }

    // Helper method to map ResultSet to Employee object
    private Employee mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setRoleName(resultSet.getString("RoleName"));
        employee.setEmployeeID(resultSet.getInt("EmployeeID"));
        employee.setEmployeeFname(resultSet.getString("EmployeeFname"));
        employee.setEmployeeLname(resultSet.getString("EmployeeLname"));
        employee.setEmployeeEmail(resultSet.getString("EmployeeEmail"));
        return employee;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
    
    // Retrieve password by Email
    public String getPasswordByEmail(String employeeEmail){
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT EmployeePasswordHash FROM Employee WHERE EmployeeEmail = ?")) {
            
            preparedStatement.setString(1, employeeEmail);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return resultSet.getString("EmployeePasswordHash");
                }
            }
        }
        catch(SQLException e) {
            handleSQLException(e);
        }
        
        return null;
    }
    
    public Employee getEmployeeByEmail(String employeeEmail){
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT RoleName, EmployeeID, EmployeeFname, EmployeeLname, EmployeeEmail FROM Employee WHERE EmployeeEmail = ?")) {

            preparedStatement.setString(1, employeeEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToEmployee(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;        
    }
}
