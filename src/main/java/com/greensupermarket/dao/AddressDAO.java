package com.greensupermarket.dao;

import com.greensupermarket.model.Address;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {

    private final ConnectionManager connectionManager;

    public AddressDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new address
    public boolean createAddress(Address address) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Address (CustomerID, AddressType, AddressStreet, AddressCity, AddressState, AddressZipCode, AddressCountry) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, address.getCustomerID());
            preparedStatement.setString(2, address.getAddressType());
            preparedStatement.setString(3, address.getAddressStreet());
            preparedStatement.setString(4, address.getAddressCity());
            preparedStatement.setString(5, address.getAddressState());
            preparedStatement.setString(6, address.getAddressZipCode());
            preparedStatement.setString(7, address.getAddressCountry());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve address by ID
    public Address getAddressById(int addressId) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Address WHERE AddressID = ?")) {

            preparedStatement.setInt(1, addressId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAddress(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update address
    public boolean updateAddress(Address address) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Address SET CustomerID=?, AddressType=?, AddressStreet=?, AddressCity=?, AddressState=?, AddressZipCode=?, AddressCountry=? WHERE AddressID=?")) {

            preparedStatement.setInt(1, address.getCustomerID());
            preparedStatement.setString(2, address.getAddressType());
            preparedStatement.setString(3, address.getAddressStreet());
            preparedStatement.setString(4, address.getAddressCity());
            preparedStatement.setString(5, address.getAddressState());
            preparedStatement.setString(6, address.getAddressZipCode());
            preparedStatement.setString(7, address.getAddressCountry());
            preparedStatement.setInt(8, address.getAddressID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete address by ID
    public boolean deleteAddress(int addressId) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Address WHERE AddressID=?")) {

            preparedStatement.setInt(1, addressId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all addresses
    public List<Address> getAllAddresses() {
        List<Address> addressList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Address");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Address address = mapResultSetToAddress(resultSet);
                addressList.add(address);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return addressList;
    }

    // Helper method to map ResultSet to Address object
    private Address mapResultSetToAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setCustomerID(resultSet.getInt("CustomerID"));
        address.setAddressID(resultSet.getInt("AddressID"));
        address.setAddressType(resultSet.getString("AddressType"));
        address.setAddressStreet(resultSet.getString("AddressStreet"));
        address.setAddressCity(resultSet.getString("AddressCity"));
        address.setAddressState(resultSet.getString("AddressState"));
        address.setAddressZipCode(resultSet.getString("AddressZipCode"));
        address.setAddressCountry(resultSet.getString("AddressCountry"));
        return address;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
