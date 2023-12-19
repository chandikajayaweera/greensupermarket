package com.greensupermarket.dao;


import com.greensupermarket.model.Unit;
import com.greensupermarket.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDAO {

    private final ConnectionManager connectionManager;

    public UnitDAO() {
        this.connectionManager = new ConnectionManager();
    }

    // Create a new unit
    public boolean addUnit(Unit unit) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO Unit (UnitName, UnitAbbreviation) VALUES (?, ?)")) {

            preparedStatement.setString(1, unit.getUnitName());
            preparedStatement.setString(2, unit.getUnitAbbreviation());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Retrieve unit by name
    public Unit getUnitByName(String unitName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Unit WHERE UnitName = ?")) {

            preparedStatement.setString(1, unitName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUnit(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }
    
    // Retrieve unit by Unit Abbreviation
    public Unit getUnitByUnitAbbreviation(String unitAbbreviation) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Unit WHERE unitAbbreviation = ?")) {

            preparedStatement.setString(1, unitAbbreviation);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUnit(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    // Delete unit by name
    public boolean deleteUnit(String unitName) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Unit WHERE UnitName=?")) {

            preparedStatement.setString(1, unitName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    // Get all units
    public List<Unit> getAllUnits() {
        List<Unit> unitList = new ArrayList<>();

        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Unit");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Unit unit = mapResultSetToUnit(resultSet);
                unitList.add(unit);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return unitList;
    }

    // Helper method to map ResultSet to Unit object
    private Unit mapResultSetToUnit(ResultSet resultSet) throws SQLException {
        Unit unit = new Unit();
        unit.setUnitName(resultSet.getString("UnitName"));
        unit.setUnitAbbreviation(resultSet.getString("UnitAbbreviation"));
        return unit;
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
        // Log or handle the exception as needed
        e.printStackTrace();
    }
}
