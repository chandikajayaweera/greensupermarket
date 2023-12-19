package com.greensupermarket.model;

public class Unit {

    private String unitName;
    private String unitAbbreviation;

    // Default constructor
    public Unit() {
    }

    // Parameterized constructor
    public Unit(String unitName, String unitAbbreviation) {
        this.unitName = unitName;
        this.unitAbbreviation = unitAbbreviation;
    }

    // Getters and setters
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAbbreviation() {
        return unitAbbreviation;
    }

    public void setUnitAbbreviation(String unitAbbreviation) {
        this.unitAbbreviation = unitAbbreviation;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Unit{" +
                "unitName='" + unitName + '\'' +
                ", unitAbbreviation='" + unitAbbreviation + '\'' +
                '}';
    }
}