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
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Product (BrandName, ProductStock, SubCategoryName, ProductSKU, ProductName, ProductDescription, ProductUnitPrice, ProductImageURL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, product.getBrandName());
            preparedStatement.setInt(2, product.getProductStock());
            preparedStatement.setString(3, product.getSubCategoryName());
            preparedStatement.setString(4, product.getProductSKU());
            preparedStatement.setString(5, product.getProductName());
            preparedStatement.setString(6, product.getProductDescription());
            preparedStatement.setDouble(7, product.getProductUnitPrice());
            preparedStatement.setString(8, product.getProductImageURL());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve product by ID
    public Product getProductById(int productID) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Product WHERE ProductID = ?")) {

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
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Product SET BrandName=?, SubCategoryName=?, ProductSKU=?, ProductName=?, ProductDescription=?, ProductUnitPrice=?, ProductImageURL=? WHERE ProductID=?")) {

            preparedStatement.setString(1, product.getBrandName());
            preparedStatement.setString(2, product.getSubCategoryName());
            preparedStatement.setString(3, product.getProductSKU());
            preparedStatement.setString(4, product.getProductName());
            preparedStatement.setString(5, product.getProductDescription());
            preparedStatement.setDouble(6, product.getProductUnitPrice());
            preparedStatement.setString(7, product.getProductImageURL());
            preparedStatement.setInt(8, product.getProductID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }
    
    // Update product stock
    public boolean updateProductStock(Product product){
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Product SET ProductStock=? WHERE ProductID=?")) {
            
            preparedStatement.setInt(1, product.getProductStock());
            preparedStatement.setInt(2, product.getProductID());
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }
    
    // Update product Unit Price
    public boolean updateProductUnitPrice(Product product){
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Product SET ProductUnitPrice=? WHERE ProductID=?")) {
            
            preparedStatement.setDouble(1, product.getProductUnitPrice());
            preparedStatement.setInt(2, product.getProductID());
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete product by ID
    public boolean deleteProduct(int productID) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Product WHERE ProductID=?")) {

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

        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Product"); ResultSet resultSet = preparedStatement.executeQuery()) {

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
        product.setBrandName(resultSet.getString("BrandName"));
        product.setProductStock(resultSet.getInt("ProductStock"));
        product.setSubCategoryName(resultSet.getString("SubCategoryName"));
        product.setProductSKU(resultSet.getString("ProductSKU"));
        product.setProductName(resultSet.getString("ProductName"));
        product.setProductDescription(resultSet.getString("ProductDescription"));
        product.setProductUnitPrice(resultSet.getDouble("ProductUnitPrice"));
        product.setProductImageURL(resultSet.getString("ProductImageURL"));
        return product;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }

    // Get product by ProductSKU
    public Product getProductBySKU(String productSKU) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Product WHERE ProductSKU =?")) {

            preparedStatement.setString(1, productSKU);

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

    // Get product by ProductName
    public Product getProductByName(String productName) {
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Product WHERE ProductName =?")) {

            preparedStatement.setString(1, productName);

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

    // Get Products by SubCategoryName
    public List<Product> getProductsBySubCategoryName(String subCategoryName) {

        List<Product> productList = new ArrayList<>();
        try (Connection con = connectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Product WHERE SubCategoryName = ?")) {

            preparedStatement.setString(1, subCategoryName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = mapResultSetToProduct(resultSet);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return productList;
    }

}
