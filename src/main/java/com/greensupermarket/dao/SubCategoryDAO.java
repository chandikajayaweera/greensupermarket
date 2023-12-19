package com.greensupermarket.dao;

import com.greensupermarket.util.ConnectionManager;
import com.greensupermarket.model.SubCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryDAO {

    private final ConnectionManager connectionManager;

    public SubCategoryDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Add subcategory
    public boolean addSubCategory(SubCategory subCategory) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO SubCategory (CategoryName, SubCategoryName) VALUES (?, ?)")) {

            preparedStatement.setString(1, subCategory.getCategoryName());
            preparedStatement.setString(2, subCategory.getSubCategoryName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve subcategory by name
    public SubCategory getSubCategoryByName(String subCategoryName) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM SubCategory WHERE SubCategoryName = ?")) {

            preparedStatement.setString(1, subCategoryName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToSubCategory(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Delete subcategory by name
    public boolean deleteSubCategory(String subCategoryName) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM SubCategory WHERE SubCategoryName = ?")) {

            preparedStatement.setString(1, subCategoryName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all subcategories
    public List<SubCategory> getAllSubCategories() {
        List<SubCategory> subCategoryList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM SubCategory"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                SubCategory subCategory = mapResultSetToSubCategory(resultSet);
                subCategoryList.add(subCategory);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return subCategoryList;
    }

    // Helper method to map ResultSet to SubCategory object
    private SubCategory mapResultSetToSubCategory(ResultSet resultSet) throws SQLException {
        SubCategory subCategory = new SubCategory();
        subCategory.setCategoryName(resultSet.getString("CategoryName"));
        subCategory.setSubCategoryName(resultSet.getString("SubCategoryName"));
        return subCategory;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
