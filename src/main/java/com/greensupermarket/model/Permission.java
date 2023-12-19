package com.greensupermarket.model;

public class Permission {

    private String permissionName;
    private String permissionProperty;

    // Default constructor
    public Permission() {
    }

    // Parameterized constructor
    public Permission(String permissionName, String permissionProperty) {
        this.permissionName = permissionName;
        this.permissionProperty = permissionProperty;
    }

    // Getters and setters
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionProperty() {
        return permissionProperty;
    }

    public void setPermissionProperty(String permissionProperty) {
        this.permissionProperty = permissionProperty;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Permission{" +
                "permissionName='" + permissionName + '\'' +
                ", permissionProperty='" + permissionProperty + '\'' +
                '}';
    }
}
