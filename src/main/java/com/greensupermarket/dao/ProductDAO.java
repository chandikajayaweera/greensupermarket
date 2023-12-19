package com.greensupermarket.dao;

import com.greensupermarket.model.Product;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final ConnectionManager connectionManager;

    public ProductDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new product
    public boolean addProduct(Product product) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Product (UnitName, BrandName, ProductStock, SubCategoryName, ProductSKU, ProductName, ProductDescription, ProductUnitPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, product.getUnitName());
            preparedStatement.setString(2, product.getBrandName());
            preparedStatement.setInt(3, product.getProductStock());
            preparedStatement.setString(4, product.getSubCategoryName());
            preparedStatement.setString(5, product.getProductSKU());
            preparedStatement.setString(6, product.getProductName());
            preparedStatement.setString(7, product.getProductDescription());
            preparedStatement.setDouble(8, product.getProductUnitPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve product by ID
    public Product getProductById(int productID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Product WHERE ProductID = ?")) {

            preparedStatement.setInt(1, productID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProduct(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update product
    public boolean updateProduct(Product product) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Product SET UnitName=?, BrandName=?, ProductStock=?, SubCategoryName=?, ProductSKU=?, ProductName=?, ProductDescription=?, ProductUnitPrice=? WHERE ProductID=?")) {

            preparedStatement.setString(1, product.getUnitName());
            preparedStatement.setString(2, product.getBrandName());
            preparedStatement.setInt(3, product.getProductStock());
            preparedStatement.setString(4, product.getSubCategoryName());
            preparedStatement.setString(5, product.getProductSKU());
            preparedStatement.setString(6, product.getProductName());
            preparedStatement.setString(7, product.getProductDescription());
            preparedStatement.setDouble(8, product.getProductUnitPrice());
            preparedStatement.setInt(9, product.getProductID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete product by ID
    public boolean deleteProduct(int productID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Product WHERE ProductID=?")) {

            preparedStatement.setInt(1, productID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all products
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Product");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = mapResultSetToProduct(resultSet);
                productList.add(product);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return productList;
    }

    // Helper method to map ResultSet to Product object
    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductID(resultSet.getInt("ProductID"));
        product.setUnitName(resultSet.getString("UnitName"));
        product.setBrandName(resultSet.getString("BrandName"));
        product.setProductStock(resultSet.getInt("ProductStock"));
        product.setSubCategoryName(resultSet.getString("SubCategoryName"));
        product.setProductSKU(resultSet.getString("ProductSKU"));
        product.setProductName(resultSet.getString("ProductName"));
        product.setProductDescription(resultSet.getString("ProductDescription"));
        product.setProductUnitPrice(resultSet.getDouble("ProductUnitPrice"));
        return product;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
