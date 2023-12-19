package com.greensupermarket.dao;

import com.greensupermarket.util.ConnectionManager;
import com.greensupermarket.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    private final ConnectionManager connectionManager;

    public RoleDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new role
    public boolean addRole(Role role) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Role (RoleName) VALUES (?)")) {

            preparedStatement.setString(2, role.getRoleName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all roles
    public List<Role> getAllRoles() {
        List<Role> roleList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Role");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Role role = mapResultSetToRole(resultSet);
                roleList.add(role);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return roleList;
    }

    // Helper method to map ResultSet to Role object
    private Role mapResultSetToRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setRoleName(resultSet.getString("RoleName"));
        return role;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
