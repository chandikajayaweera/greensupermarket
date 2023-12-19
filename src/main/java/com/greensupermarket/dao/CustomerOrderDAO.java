package com.greensupermarket.dao;

import com.greensupermarket.model.CustomerOrder;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerOrderDAO {

    private final ConnectionManager connectionManager;

    public CustomerOrderDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new customer order
    public boolean createCustomerOrder(CustomerOrder customerOrder) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO CustomerOrder (CustomerID, ShippingAddressID, BillingAddressID, CustomerOrderDate, CustomerOrderStatus) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, customerOrder.getCustomerID());
            preparedStatement.setInt(2, customerOrder.getShippingAddressID());
            preparedStatement.setInt(3, customerOrder.getBillingAddressID());
            preparedStatement.setDate(4, new java.sql.Date(customerOrder.getCustomerOrderDate().getTime()));
            preparedStatement.setString(5, customerOrder.getCustomerOrderStatus());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve customer order by ID
    public CustomerOrder getCustomerOrderById(int customerOrderID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM CustomerOrder WHERE CustomerOrderID = ?")) {

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
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE CustomerOrder SET CustomerID=?, ShippingAddressID=?, BillingAddressID=?, CustomerOrderDate=?, CustomerOrderStatus=? WHERE CustomerOrderID=?")) {

            preparedStatement.setInt(1, customerOrder.getCustomerID());
            preparedStatement.setInt(2, customerOrder.getShippingAddressID());
            preparedStatement.setInt(3, customerOrder.getBillingAddressID());
            preparedStatement.setDate(4, new java.sql.Date(customerOrder.getCustomerOrderDate().getTime()));
            preparedStatement.setString(5, customerOrder.getCustomerOrderStatus());
            preparedStatement.setInt(6, customerOrder.getCustomerOrderID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete customer order by ID
    public boolean deleteCustomerOrder(int customerOrderID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM CustomerOrder WHERE CustomerOrderID=?")) {

            preparedStatement.setInt(1, customerOrderID);

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

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM CustomerOrder");
             ResultSet resultSet = preparedStatement.executeQuery()) {

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
        customerOrder.setShippingAddressID(resultSet.getInt("ShippingAddressID"));
        customerOrder.setBillingAddressID(resultSet.getInt("BillingAddressID"));
        customerOrder.setCustomerOrderDate(resultSet.getDate("CustomerOrderDate"));
        customerOrder.setCustomerOrderStatus(resultSet.getString("CustomerOrderStatus"));
        return customerOrder;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
