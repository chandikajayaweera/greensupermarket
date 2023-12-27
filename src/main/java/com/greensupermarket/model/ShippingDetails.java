package com.greensupermarket.model;

public class ShippingDetails {

    private int customerOrderID;  // Updated from shippingDetailID
    private String recipientName;
    private String line1;
    private String line2;
    private String city;
    private String countryCode;
    private String postalCode;
    private String state;

    // Default constructor
    public ShippingDetails() {
    }

    // Parameterized constructor
    public ShippingDetails(int customerOrderID, String recipientName, String line1, String line2, String city, String countryCode, String postalCode, String state) {
        this.customerOrderID = customerOrderID;
        this.recipientName = recipientName;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.countryCode = countryCode;
        this.postalCode = postalCode;
        this.state = state;
    }

    // Getters and setters
    public int getCustomerOrderID() {
        return customerOrderID;
    }

    public void setCustomerOrderID(int customerOrderID) {
        this.customerOrderID = customerOrderID;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "ShippingDetails{" +
                "customerOrderID=" + customerOrderID +
                ", recipientName='" + recipientName + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
