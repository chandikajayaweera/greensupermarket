package com.greensupermarket.dao;

import com.greensupermarket.model.VariationValue;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VariationValueDAO {

    private final ConnectionManager connectionManager;

    public VariationValueDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new variation value
    public boolean createVariationValue(VariationValue variationValue) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO VariationValue (VariationName, VariationValueName) VALUES (?, ?)")) {

            preparedStatement.setString(1, variationValue.getVariationName());
            preparedStatement.setString(2, variationValue.getVariationValueName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve variation value by name and variation name
    public VariationValue getVariationValueByName(String variationName, String variationValueName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "SELECT * FROM VariationValue WHERE VariationName = ? AND VariationValueName = ?")) {

            preparedStatement.setString(1, variationName);
            preparedStatement.setString(2, variationValueName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToVariationValue(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update variation value
    public boolean updateVariationValue(VariationValue variationValue) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE VariationValue SET VariationValueName=? WHERE VariationName=? AND VariationValueName=?")) {

            preparedStatement.setString(1, variationValue.getVariationValueName());
            preparedStatement.setString(2, variationValue.getVariationName());
            preparedStatement.setString(3, variationValue.getVariationValueName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete variation value by name and variation name
    public boolean deleteVariationValue(String variationName, String variationValueName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "DELETE FROM VariationValue WHERE VariationName=? AND VariationValueName=?")) {

            preparedStatement.setString(1, variationName);
            preparedStatement.setString(2, variationValueName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all variation values for a given variation
    public List<VariationValue> getAllVariationValues(String variationName) {
        List<VariationValue> variationValueList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "SELECT * FROM VariationValue WHERE VariationName=?")) {

            preparedStatement.setString(1, variationName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    VariationValue variationValue = mapResultSetToVariationValue(resultSet);
                    variationValueList.add(variationValue);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return variationValueList;
    }

    // Helper method to map ResultSet to VariationValue object
    private VariationValue mapResultSetToVariationValue(ResultSet resultSet) throws SQLException {
        VariationValue variationValue = new VariationValue();
        variationValue.setVariationName(resultSet.getString("VariationName"));
        variationValue.setVariationValueName(resultSet.getString("VariationValueName"));
        return variationValue;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
