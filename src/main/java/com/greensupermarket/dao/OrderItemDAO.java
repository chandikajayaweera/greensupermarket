package com.greensupermarket.dao;

import com.greensupermarket.model.OrderItem;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    private final ConnectionManager connectionManager;

    public OrderItemDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new order item
    public boolean addOrderItem(OrderItem orderItem) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO OrderItem (CustomerOrderID, ProductID, OrderItemQuantity, OrderItemUnitPrice) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, orderItem.getCustomerOrderID());
            preparedStatement.setInt(2, orderItem.getProductID());
            preparedStatement.setInt(3, orderItem.getOrderItemQuantity());
            preparedStatement.setDouble(4, orderItem.getOrderItemUnitPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve order item by order ID and product ID
    public OrderItem getOrderItemByIds(int customerOrderID, int productID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "SELECT * FROM OrderItem WHERE CustomerOrderID = ? AND ProductID = ?")) {

            preparedStatement.setInt(1, customerOrderID);
            preparedStatement.setInt(2, productID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToOrderItem(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update order item
    public boolean updateOrderItem(OrderItem orderItem) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE OrderItem SET OrderItemQuantity=?, OrderItemUnitPrice=? WHERE CustomerOrderID=? AND ProductID=?")) {

            preparedStatement.setInt(1, orderItem.getOrderItemQuantity());
            preparedStatement.setDouble(2, orderItem.getOrderItemUnitPrice());
            preparedStatement.setInt(3, orderItem.getCustomerOrderID());
            preparedStatement.setInt(4, orderItem.getProductID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete order item by order ID and product ID
    public boolean deleteOrderItem(int customerOrderID, int productID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "DELETE FROM OrderItem WHERE CustomerOrderID=? AND ProductID=?")) {

            preparedStatement.setInt(1, customerOrderID);
            preparedStatement.setInt(2, productID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all order items for a given order ID
    public List<OrderItem> getAllOrderItemsByOrderId(int customerOrderID) {
        List<OrderItem> orderItemList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "SELECT * FROM OrderItem WHERE CustomerOrderID=?")) {

            preparedStatement.setInt(1, customerOrderID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OrderItem orderItem = mapResultSetToOrderItem(resultSet);
                    orderItemList.add(orderItem);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return orderItemList;
    }

    // Helper method to map ResultSet to OrderItem object
    private OrderItem mapResultSetToOrderItem(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setCustomerOrderID(resultSet.getInt("CustomerOrderID"));
        orderItem.setProductID(resultSet.getInt("ProductID"));
        orderItem.setOrderItemQuantity(resultSet.getInt("OrderItemQuantity"));
        orderItem.setOrderItemUnitPrice(resultSet.getDouble("OrderItemUnitPrice"));
        return orderItem;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
