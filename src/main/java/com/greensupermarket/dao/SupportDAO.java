package com.greensupermarket.dao;

import com.greensupermarket.util.ConnectionManager;
import com.greensupermarket.model.Support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupportDAO {

    private final ConnectionManager connectionManager;

    public SupportDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Add support request
    public boolean addSupportRequest(Support support) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Support (SupportDate, SupportName, SupportEmail, SupportMessage) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setObject(1, support.getSupportDate());
            preparedStatement.setString(2, support.getSupportName());
            preparedStatement.setString(3, support.getSupportEmail());
            preparedStatement.setString(4, support.getSupportMessage());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve support request by ID
    public Support getSupportRequestById(int supportID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Support WHERE SupportID = ?")) {

            preparedStatement.setInt(1, supportID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToSupport(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Get all support requests
    public List<Support> getAllSupportRequests() {
        List<Support> supportList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Support");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Support support = mapResultSetToSupport(resultSet);
                supportList.add(support);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return supportList;
    }

    // Helper method to map ResultSet to Support object
    private Support mapResultSetToSupport(ResultSet resultSet) throws SQLException {
        Support support = new Support();
        support.setSupportID(resultSet.getInt("SupportID"));
        support.setSupportDate(resultSet.getDate("SupportDate"));
        support.setSupportName(resultSet.getString("SupportName"));
        support.setSupportEmail(resultSet.getString("SupportEmail"));
        support.setSupportMessage(resultSet.getString("SupportMessage"));
        return support;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
