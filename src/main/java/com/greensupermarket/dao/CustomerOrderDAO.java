package com.greensupermarket.dao;

import com.greensupermarket.model.CustomerOrder;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDAO {

    private final ConnectionManager connectionManager;

    public CustomerOrderDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new customer order
    public boolean createCustomerOrder(CustomerOrder customerOrder) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO CustomerOrder (CustomerID, CustomerOrderDate, CustomerOrderStatus, PaymentID) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, customerOrder.getCustomerID());
            preparedStatement.setDate(2, new java.sql.Date(customerOrder.getCustomerOrderDate().getTime()));
            preparedStatement.setString(3, customerOrder.getCustomerOrderStatus());
            preparedStatement.setString(4, customerOrder.getPaymentID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve customer order by ID
    public CustomerOrder getCustomerOrderById(int customerOrderID) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM CustomerOrder WHERE CustomerOrderID = ?")) {

            preparedStatement.setInt(1, customerOrderID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomerOrder(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update customer order
    public boolean updateCustomerOrder(CustomerOrder customerOrder) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE CustomerOrder SET CustomerID=?, CustomerOrderDate=?, CustomerOrderStatus=?, PaymentID=? WHERE CustomerOrderID=?")) {

            preparedStatement.setInt(1, customerOrder.getCustomerID());
            preparedStatement.setDate(2, new java.sql.Date(customerOrder.getCustomerOrderDate().getTime()));
            preparedStatement.setString(3, customerOrder.getCustomerOrderStatus());
            preparedStatement.setString(4, customerOrder.getPaymentID());
            preparedStatement.setInt(5, customerOrder.getCustomerOrderID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all customer orders
    public List<CustomerOrder> getAllCustomerOrders() {
        List<CustomerOrder> customerOrderList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM CustomerOrder"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                CustomerOrder customerOrder = mapResultSetToCustomerOrder(resultSet);
                customerOrderList.add(customerOrder);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return customerOrderList;
    }

    // Helper method to map ResultSet to CustomerOrder object
    private CustomerOrder mapResultSetToCustomerOrder(ResultSet resultSet) throws SQLException {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomerOrderID(resultSet.getInt("CustomerOrderID"));
        customerOrder.setCustomerID(resultSet.getInt("CustomerID"));
        customerOrder.setCustomerOrderDate(resultSet.getDate("CustomerOrderDate"));
        customerOrder.setCustomerOrderStatus(resultSet.getString("CustomerOrderStatus"));
        customerOrder.setPaymentID(resultSet.getString("PaymentID"));
        return customerOrder;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }

    // Retrieve customer order ID by PaymentID
    public int getCustomerOrderIDByPaymentID(String paymentID) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT CustomerOrderID FROM CustomerOrder WHERE PaymentID = ?")) {

            preparedStatement.setString(1, paymentID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("CustomerOrderID");
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return -1;
    }

    // Retrieve all customer orders by CustomerID
    public List<CustomerOrder> getAllCustomerOrdersByCustomerID(int customerID) {
        List<CustomerOrder> customerOrderList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM CustomerOrder WHERE CustomerID = ?")) {

            preparedStatement.setInt(1, customerID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CustomerOrder customerOrder = mapResultSetToCustomerOrder(resultSet);
                    customerOrderList.add(customerOrder);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return customerOrderList;
    }

}
