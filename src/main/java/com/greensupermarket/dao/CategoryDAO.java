package com.greensupermarket.dao;

import com.greensupermarket.model.Category;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private final ConnectionManager connectionManager;

    public CategoryDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Add a new category
    public boolean addCategory(Category category) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Category (CategoryName, CategoryImageURL) VALUES (?, ?)")) {

            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryImageURL());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve category by name
    public Category getCategoryByName(String categoryName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Category WHERE CategoryName = ?")) {

            preparedStatement.setString(1, categoryName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCategory(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update category
    public boolean updateCategory(Category category) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Category SET CategoryImageURL=? WHERE CategoryName=?")) {
            
            preparedStatement.setString(1, category.getCategoryImageURL());
            preparedStatement.setString(2, category.getCategoryName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete category by name
    public boolean deleteCategory(String categoryName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Category WHERE CategoryName=?")) {

            preparedStatement.setString(1, categoryName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all categories
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Category");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Category category = mapResultSetToCategory(resultSet);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return categoryList;
    }

    // Helper method to map ResultSet to Category object
    private Category mapResultSetToCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setCategoryName(resultSet.getString("CategoryName"));
        category.setCategoryImageURL(resultSet.getString("CategoryImageURL"));
        return category;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
