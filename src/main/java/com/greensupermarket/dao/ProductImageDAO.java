package com.greensupermarket.dao;

import com.greensupermarket.model.ProductImage;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImageDAO {

    private final ConnectionManager connectionManager;

    public ProductImageDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new product image
    public boolean addProductImage(ProductImage productImage) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO ProductImage (ProductID, ProductImageURL) VALUES (?, ?)")) {

            preparedStatement.setInt(1, productImage.getProductID());
            preparedStatement.setString(2, productImage.getProductImageURL());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve product image by ID
    public ProductImage getProductImageById(int productImageID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM ProductImage WHERE ProductImageID = ?")) {

            preparedStatement.setInt(1, productImageID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProductImage(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update product image
    public boolean updateProductImage(ProductImage productImage) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE ProductImage SET ProductID=?, ProductImageURL=? WHERE ProductImageID=?")) {

            preparedStatement.setInt(1, productImage.getProductID());
            preparedStatement.setString(2, productImage.getProductImageURL());
            preparedStatement.setInt(3, productImage.getProductImageID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete product image by ID
    public boolean deleteProductImage(int productImageID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM ProductImage WHERE ProductImageID=?")) {

            preparedStatement.setInt(1, productImageID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all product images for a specific product
    public List<ProductImage> getProductImagesByProductId(int productID) {
        List<ProductImage> productImageList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM ProductImage WHERE ProductID = ?");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            preparedStatement.setInt(1, productID);

            while (resultSet.next()) {
                ProductImage productImage = mapResultSetToProductImage(resultSet);
                productImageList.add(productImage);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return productImageList;
    }

    // Helper method to map ResultSet to ProductImage object
    private ProductImage mapResultSetToProductImage(ResultSet resultSet) throws SQLException {
        ProductImage productImage = new ProductImage();
        productImage.setProductID(resultSet.getInt("ProductID"));
        productImage.setProductImageID(resultSet.getInt("ProductImageID"));
        productImage.setProductImageURL(resultSet.getString("ProductImageURL"));
        return productImage;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
