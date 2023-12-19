package com.greensupermarket.dao;

import com.greensupermarket.model.Customer;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final ConnectionManager connectionManager;

    public CustomerDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new customer
    public boolean createCustomer(Customer customer) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Customer (CustomerFname, CustomerLname, CustomerEmail, CustomerPnumber, CustomerPasswordHash) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, customer.getCustomerFname());
            preparedStatement.setString(2, customer.getCustomerLname());
            preparedStatement.setString(3, customer.getCustomerEmail());
            preparedStatement.setString(4, customer.getCustomerPnumber());
            preparedStatement.setString(5, customer.getCustomerPasswordHash());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve customer by ID
    public Customer getCustomerById(int customerID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?")) {

            preparedStatement.setInt(1, customerID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update customer
    public boolean updateCustomer(Customer customer) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Customer SET CustomerFname=?, CustomerLname=?, CustomerEmail=?, CustomerPnumber=?, CustomerPasswordHash=? WHERE CustomerID=?")) {

            preparedStatement.setString(1, customer.getCustomerFname());
            preparedStatement.setString(2, customer.getCustomerLname());
            preparedStatement.setString(3, customer.getCustomerEmail());
            preparedStatement.setString(4, customer.getCustomerPnumber());
            preparedStatement.setString(5, customer.getCustomerPasswordHash());
            preparedStatement.setInt(6, customer.getCustomerID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete customer by ID
    public boolean deleteCustomer(int customerID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Customer WHERE CustomerID=?")) {

            preparedStatement.setInt(1, customerID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Customer");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Customer customer = mapResultSetToCustomer(resultSet);
                customerList.add(customer);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return customerList;
    }

    // Helper method to map ResultSet to Customer object
    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerID(resultSet.getInt("CustomerID"));
        customer.setCustomerFname(resultSet.getString("CustomerFname"));
        customer.setCustomerLname(resultSet.getString("CustomerLname"));
        customer.setCustomerEmail(resultSet.getString("CustomerEmail"));
        customer.setCustomerPnumber(resultSet.getString("CustomerPnumber"));
        customer.setCustomerPasswordHash(resultSet.getString("CustomerPasswordHash"));
        return customer;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
