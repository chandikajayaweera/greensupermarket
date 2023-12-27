package com.greensupermarket.model;

import java.util.Date;

public class CustomerOrder {

    private int customerOrderID;
    private int customerID;
    private int shippingAddressID;
    private Date customerOrderDate;
    private String customerOrderStatus;
    private String paymentID;

    // Default constructor
    public CustomerOrder() {
    }

    // Parameterized constructor
    public CustomerOrder(int customerOrderID, int customerID, int shippingAddressID, Date customerOrderDate, String customerOrderStatus, String paymentID) {
        this.customerOrderID = customerOrderID;
        this.customerID = customerID;
        this.shippingAddressID = shippingAddressID;
        this.customerOrderDate = customerOrderDate;
        this.customerOrderStatus = customerOrderStatus;
        this.paymentID = paymentID;
    }

    // Getters and setters
    public int getCustomerOrderID() {
        return customerOrderID;
    }

    public void setCustomerOrderID(int customerOrderID) {
        this.customerOrderID = customerOrderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getShippingAddressID() {
        return shippingAddressID;
    }

    public void setShippingAddressID(int shippingAddressID) {
        this.shippingAddressID = shippingAddressID;
    }

    public Date getCustomerOrderDate() {
        return customerOrderDate;
    }

    public void setCustomerOrderDate(Date customerOrderDate) {
        this.customerOrderDate = customerOrderDate;
    }

    public String getCustomerOrderStatus() {
        return customerOrderStatus;
    }

    public void setCustomerOrderStatus(String customerOrderStatus) {
        this.customerOrderStatus = customerOrderStatus;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "CustomerOrder{" +
                "customerOrderID=" + customerOrderID +
                ", customerID=" + customerID +
                ", shippingAddressID=" + shippingAddressID +
                ", customerOrderDate=" + customerOrderDate +
                ", customerOrderStatus='" + customerOrderStatus + '\'' +
                ", paymentID='" + paymentID + '\'' +
                '}';
    }
}
