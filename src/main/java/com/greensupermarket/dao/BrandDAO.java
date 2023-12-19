package com.greensupermarket.dao;

import com.greensupermarket.model.Brand;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {

    private final ConnectionManager connectionManager;

    public BrandDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new brand
    public boolean addBrand(Brand brand) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Brand (BrandName, BrandLogoURL) VALUES (?, ?)")) {

            preparedStatement.setString(1, brand.getBrandName());
            preparedStatement.setString(2, brand.getBrandLogoURL());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve brand by name
    public Brand getBrandByName(String brandName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Brand WHERE BrandName = ?")) {

            preparedStatement.setString(1, brandName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBrand(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update brand
    public boolean updateBrand(Brand brand) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Brand SET BrandLogoURL=? WHERE BrandName=?")) {

            preparedStatement.setString(1, brand.getBrandLogoURL());
            preparedStatement.setString(2, brand.getBrandName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete brand by name
    public boolean deleteBrand(String brandName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Brand WHERE BrandName=?")) {

            preparedStatement.setString(1, brandName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all brands
    public List<Brand> getAllBrands() {
        List<Brand> brandList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Brand");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Brand brand = mapResultSetToBrand(resultSet);
                brandList.add(brand);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return brandList;
    }

    // Helper method to map ResultSet to Brand object
    private Brand mapResultSetToBrand(ResultSet resultSet) throws SQLException {
        Brand brand = new Brand();
        brand.setBrandName(resultSet.getString("BrandName"));
        brand.setBrandLogoURL(resultSet.getString("BrandLogoURL"));
        return brand;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
