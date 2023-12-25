package com.greensupermarket.model;

public class OrderItem {

    private int customerOrderID;
    private int productID;
    private String productName;
    private String productImageURL;
    private int orderItemQuantity;
    private double orderItemUnitPrice;

    // Default constructor
    public OrderItem() {
    }

    // Parameterized constructor
    public OrderItem(int customerOrderID, int productID, String productName, String productImageURL, int orderItemQuantity, double orderItemUnitPrice) {
        this.customerOrderID = customerOrderID;
        this.productID = productID;
        this.productName = productName;
        this.productImageURL = productImageURL;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemUnitPrice = orderItemUnitPrice;
    }

    // Getters and setters
    public int getCustomerOrderID() {
        return customerOrderID;
    }

    public void setCustomerOrderID(int customerOrderID) {
        this.customerOrderID = customerOrderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public String getProductImageURL(){
        return productImageURL;
    }
    
    public void setProductImageURL(String productImageURL){
        this.productImageURL = productImageURL;
    }

    public int getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(int orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }

    public double getOrderItemUnitPrice() {
        return orderItemUnitPrice;
    }

    public void setOrderItemUnitPrice(double orderItemUnitPrice) {
        this.orderItemUnitPrice = orderItemUnitPrice;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "OrderItem{" +
                "customerOrderID=" + customerOrderID +
                ", productID=" + productID +
                ", productName=" + productName +
                ", productImageURL=" + productImageURL +
                ", orderItemQuantity=" + orderItemQuantity +
                ", orderItemUnitPrice=" + orderItemUnitPrice +
                '}';
    }
}
