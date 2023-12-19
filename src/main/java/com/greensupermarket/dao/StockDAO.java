package com.greensupermarket.dao;

import com.greensupermarket.util.ConnectionManager;
import com.greensupermarket.model.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    private final ConnectionManager connectionManager;

    public StockDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Add stock
    public boolean addStock(Stock stock) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Stock (ProductID, StockID, StockQuantity, StockDate, StockAvailable) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, stock.getProductID());
            preparedStatement.setInt(2, stock.getStockID());
            preparedStatement.setInt(3, stock.getStockQuantity());
            preparedStatement.setDate(4, new java.sql.Date(stock.getStockDate().getTime()));
            preparedStatement.setBoolean(5, stock.isStockAvailable());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve stock by product ID
    public Stock getStockByProductID(int productID) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Stock WHERE ProductID = ?")) {

            preparedStatement.setInt(1, productID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToStock(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Get all stocks
    public List<Stock> getAllStocks() {
        List<Stock> stockList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Stock");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Stock stock = mapResultSetToStock(resultSet);
                stockList.add(stock);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return stockList;
    }

    // Helper method to map ResultSet to Stock object
    private Stock mapResultSetToStock(ResultSet resultSet) throws SQLException {
        Stock stock = new Stock();
        stock.setProductID(resultSet.getInt("ProductID"));
        stock.setStockID(resultSet.getInt("StockID"));
        stock.setStockQuantity(resultSet.getInt("StockQuantity"));
        stock.setStockDate(resultSet.getDate("StockDate"));
        stock.setStockAvailable(resultSet.getBoolean("StockAvailable"));
        return stock;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
