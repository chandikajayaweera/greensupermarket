package com.greensupermarket.dao;

import com.greensupermarket.model.Permission;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionDAO {

    private final ConnectionManager connectionManager;

    public PermissionDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new permission
    public boolean addPermission(Permission permission) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Permission (PermissionName, PermissionProperty) VALUES (?, ?)")) {

            preparedStatement.setString(1, permission.getPermissionName());
            preparedStatement.setString(2, permission.getPermissionProperty());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve permission by name
    public Permission getPermissionByName(String permissionName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "SELECT * FROM Permission WHERE PermissionName = ?")) {

            preparedStatement.setString(1, permissionName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToPermission(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Update permission
    public boolean updatePermission(Permission permission) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE Permission SET PermissionProperty=? WHERE PermissionName=?")) {

            preparedStatement.setString(1, permission.getPermissionProperty());
            preparedStatement.setString(2, permission.getPermissionName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Delete permission by name
    public boolean deletePermission(String permissionName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "DELETE FROM Permission WHERE PermissionName=?")) {

            preparedStatement.setString(1, permissionName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all permissions
    public List<Permission> getAllPermissions() {
        List<Permission> permissionList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "SELECT * FROM Permission");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Permission permission = mapResultSetToPermission(resultSet);
                permissionList.add(permission);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return permissionList;
    }

    // Helper method to map ResultSet to Permission object
    private Permission mapResultSetToPermission(ResultSet resultSet) throws SQLException {
        Permission permission = new Permission();
        permission.setPermissionName(resultSet.getString("PermissionName"));
        permission.setPermissionProperty(resultSet.getString("PermissionProperty"));
        return permission;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
