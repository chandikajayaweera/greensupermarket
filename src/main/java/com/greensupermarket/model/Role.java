package com.greensupermarket.model;

public class Role {

    private String roleName;

    // Default constructor
    public Role() {
    }

    // Parameterized constructor
    public Role(String roleName) {
        this.roleName = roleName;
    }

    // Getters and setters
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}