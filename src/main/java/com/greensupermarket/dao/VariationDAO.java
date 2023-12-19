package com.greensupermarket.dao;

import com.greensupermarket.model.Variation;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VariationDAO {

    private final ConnectionManager connectionManager;

    public VariationDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new variation
    public boolean createVariation(Variation variation) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Variation (VariationName, VariationDescription) VALUES (?, ?)")) {

            preparedStatement.setString(1, variation.getVariationName());
            preparedStatement.setString(2, variation.getVariationDescription());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve variation by name
    public Variation getVariationByName(String variationName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Variation WHERE VariationName = ?")) {

            preparedStatement.setString(1, variationName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToVariation(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update variation
    public boolean updateVariation(Variation variation) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Variation SET VariationDescription=? WHERE VariationName=?")) {

            preparedStatement.setString(1, variation.getVariationDescription());
            preparedStatement.setString(2, variation.getVariationName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete variation by name
    public boolean deleteVariation(String variationName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Variation WHERE VariationName=?")) {

            preparedStatement.setString(1, variationName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all variations
    public List<Variation> getAllVariations() {
        List<Variation> variationList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Variation");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Variation variation = mapResultSetToVariation(resultSet);
                variationList.add(variation);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return variationList;
    }

    // Helper method to map ResultSet to Variation object
    private Variation mapResultSetToVariation(ResultSet resultSet) throws SQLException {
        Variation variation = new Variation();
        variation.setVariationName(resultSet.getString("VariationName"));
        variation.setVariationDescription(resultSet.getString("VariationDescription"));
        return variation;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
