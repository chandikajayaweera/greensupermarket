package com.greensupermarket.model;

public class Address {

    private int customerID;
    private int addressID;
    private String addressType;
    private String addressStreet;
    private String addressCity;
    private String addressState;
    private String addressZipCode;
    private String addressCountry;

    // Default constructor
    public Address() {
    }

    // Parameterized constructor
    public Address(int customerID, int addressID, String addressType, String addressStreet, String addressCity, String addressState, String addressZipCode, String addressCountry) {
        this.customerID = customerID;
        this.addressID = addressID;
        this.addressType = addressType;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipCode = addressZipCode;
        this.addressCountry = addressCountry;
    }

    // Getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Address{" +
                "customerID=" + customerID +
                ", addressID=" + addressID +
                ", addressType='" + addressType + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressState='" + addressState + '\'' +
                ", addressZipCode='" + addressZipCode + '\'' +
                ", addressCountry='" + addressCountry + '\'' +
                '}';
    }
}
