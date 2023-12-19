package com.greensupermarket.model;

public class Employee {

    private String roleName;
    private int employeeID;
    private String employeeFname;
    private String employeeLname;
    private String employeeEmail;
    private String employeePassword;

    // Default constructor
    public Employee() {
    }

    // Parameterized constructor
    public Employee(String roleName, int employeeID, String employeeFname, String employeeLname, String employeeEmail, String employeePassword) {
        this.roleName = roleName;
        this.employeeID = employeeID;
        this.employeeFname = employeeFname;
        this.employeeLname = employeeLname;
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
    }

    // Getters and setters
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeFname() {
        return employeeFname;
    }

    public void setEmployeeFname(String employeeFname) {
        this.employeeFname = employeeFname;
    }

    public String getEmployeeLname() {
        return employeeLname;
    }

    public void setEmployeeLname(String employeeLname) {
        this.employeeLname = employeeLname;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Employee{" +
                "roleName='" + roleName + '\'' +
                ", employeeID=" + employeeID +
                ", employeeFname='" + employeeFname + '\'' +
                ", employeeLname='" + employeeLname + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                '}';
    }
}
