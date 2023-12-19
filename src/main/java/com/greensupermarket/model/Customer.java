package com.greensupermarket.model;

public class Customer {

    private int customerID;
    private String customerFname;
    private String customerLname;
    private String customerEmail;
    private String customerPnumber;
    private String customerPasswordHash;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(int customerID, String customerFname, String customerLname, String customerEmail, String customerPnumber, String customerPasswordHash) {
        this.customerID = customerID;
        this.customerFname = customerFname;
        this.customerLname = customerLname;
        this.customerEmail = customerEmail;
        this.customerPnumber = customerPnumber;
        this.customerPasswordHash = customerPasswordHash;
    }

    // Getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerFname() {
        return customerFname;
    }

    public void setCustomerFname(String customerFname) {
        this.customerFname = customerFname;
    }

    public String getCustomerLname() {
        return customerLname;
    }

    public void setCustomerLname(String customerLname) {
        this.customerLname = customerLname;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPnumber() {
        return customerPnumber;
    }

    public void setCustomerPnumber(String customerPnumber) {
        this.customerPnumber = customerPnumber;
    }

    public String getCustomerPasswordHash() {
        return customerPasswordHash;
    }

    public void setCustomerPasswordHash(String customerPasswordHash) {
        this.customerPasswordHash = customerPasswordHash;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerFname='" + customerFname + '\'' +
                ", customerLname='" + customerLname + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPnumber='" + customerPnumber + '\'' +
                '}';
    }
}
