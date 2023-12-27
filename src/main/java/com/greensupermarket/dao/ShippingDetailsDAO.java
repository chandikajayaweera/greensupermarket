package com.greensupermarket.dao;

import com.greensupermarket.model.ShippingDetails;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShippingDetailsDAO {

    private final ConnectionManager connectionManager;

    public ShippingDetailsDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new shipping details
    public boolean createShippingDetails(ShippingDetails shippingDetails, int customerOrderID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO ShippingDetails (RecipientName, Line1, Line2, City, CountryCode, PostalCode, State, CustomerOrderID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, shippingDetails.getRecipientName());
            preparedStatement.setString(2, shippingDetails.getLine1());
            preparedStatement.setString(3, shippingDetails.getLine2());
            preparedStatement.setString(4, shippingDetails.getCity());
            preparedStatement.setString(5, shippingDetails.getCountryCode());
            preparedStatement.setString(6, shippingDetails.getPostalCode());
            preparedStatement.setString(7, shippingDetails.getState());
            preparedStatement.setInt(8, customerOrderID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve shipping details by CustomerOrderID
    public List<ShippingDetails> getShippingDetailsByCustomerOrderID(int customerOrderID) {
        List<ShippingDetails> shippingDetailsList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM ShippingDetails WHERE CustomerOrderID = ?")) {

            preparedStatement.setInt(1, customerOrderID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ShippingDetails shippingDetails = mapResultSetToShippingDetails(resultSet);
                    shippingDetailsList.add(shippingDetails);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return shippingDetailsList;
    }

    // Helper method to map ResultSet to ShippingDetails object
    private ShippingDetails mapResultSetToShippingDetails(ResultSet resultSet) throws SQLException {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setCustomerOrderID(resultSet.getInt("CustomerOrderID"));
        shippingDetails.setRecipientName(resultSet.getString("RecipientName"));
        shippingDetails.setLine1(resultSet.getString("Line1"));
        shippingDetails.setLine2(resultSet.getString("Line2"));
        shippingDetails.setCity(resultSet.getString("City"));
        shippingDetails.setCountryCode(resultSet.getString("CountryCode"));
        shippingDetails.setPostalCode(resultSet.getString("PostalCode"));
        shippingDetails.setState(resultSet.getString("State"));
        return shippingDetails;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
