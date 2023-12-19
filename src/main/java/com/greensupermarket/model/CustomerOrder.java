package com.greensupermarket.model;

import java.util.Date;

public class CustomerOrder {

    private int customerOrderID;
    private int customerID;
    private int shippingAddressID;
    private int billingAddressID;
    private Date customerOrderDate;
    private String customerOrderStatus;

    // Default constructor
    public CustomerOrder() {
    }

    // Parameterized constructor
    public CustomerOrder(int customerOrderID, int customerID, int shippingAddressID, int billingAddressID, Date customerOrderDate, String customerOrderStatus) {
        this.customerOrderID = customerOrderID;
        this.customerID = customerID;
        this.shippingAddressID = shippingAddressID;
        this.billingAddressID = billingAddressID;
        this.customerOrderDate = customerOrderDate;
        this.customerOrderStatus = customerOrderStatus;
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

    public int getBillingAddressID() {
        return billingAddressID;
    }

    public void setBillingAddressID(int billingAddressID) {
        this.billingAddressID = billingAddressID;
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

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "CustomerOrder{" +
                "customerOrderID=" + customerOrderID +
                ", customerID=" + customerID +
                ", shippingAddressID=" + shippingAddressID +
                ", billingAddressID=" + billingAddressID +
                ", customerOrderDate=" + customerOrderDate +
                ", customerOrderStatus='" + customerOrderStatus + '\'' +
                '}';
    }
}
