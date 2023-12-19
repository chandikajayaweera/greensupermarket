package com.greensupermarket.dao;

import com.greensupermarket.model.Feedback;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    private final ConnectionManager connectionManager;

    public FeedbackDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new feedback
    public boolean addFeedback(Feedback feedback) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Feedback (CustomerID, FeedbackID, FeedbackRating, FeedbackMessage, FeedbackDate) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, feedback.getCustomerID());
            preparedStatement.setInt(2, feedback.getFeedbackID());
            preparedStatement.setInt(3, feedback.getFeedbackRating());
            preparedStatement.setString(4, feedback.getFeedbackMessage());
            preparedStatement.setDate(5, new java.sql.Date(feedback.getFeedbackDate().getTime()));

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve feedback by ID
    public Feedback getFeedbackById(int feedbackID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Feedback WHERE FeedbackID = ?")) {

            preparedStatement.setInt(1, feedbackID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToFeedback(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update feedback
    public boolean updateFeedback(Feedback feedback) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Feedback SET CustomerID=?, FeedbackRating=?, FeedbackMessage=?, FeedbackDate=? WHERE FeedbackID=?")) {

            preparedStatement.setInt(1, feedback.getCustomerID());
            preparedStatement.setInt(2, feedback.getFeedbackRating());
            preparedStatement.setString(3, feedback.getFeedbackMessage());
            preparedStatement.setDate(4, new java.sql.Date(feedback.getFeedbackDate().getTime()));
            preparedStatement.setInt(5, feedback.getFeedbackID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete feedback by ID
    public boolean deleteFeedback(int feedbackID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Feedback WHERE FeedbackID=?")) {

            preparedStatement.setInt(1, feedbackID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all feedback
    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Feedback");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Feedback feedback = mapResultSetToFeedback(resultSet);
                feedbackList.add(feedback);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return feedbackList;
    }

    // Helper method to map ResultSet to Feedback object
    private Feedback mapResultSetToFeedback(ResultSet resultSet) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setCustomerID(resultSet.getInt("CustomerID"));
        feedback.setFeedbackID(resultSet.getInt("FeedbackID"));
        feedback.setFeedbackRating(resultSet.getInt("FeedbackRating"));
        feedback.setFeedbackMessage(resultSet.getString("FeedbackMessage"));
        feedback.setFeedbackDate(resultSet.getDate("FeedbackDate"));
        return feedback;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
