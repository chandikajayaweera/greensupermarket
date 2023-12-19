package com.greensupermarket.model;

import java.util.Date;

public class Stock {

    private int productID;
    private int stockID;
    private int stockQuantity;
    private Date stockDate;
    private boolean stockAvailable;

    // Default constructor
    public Stock() {
    }

    // Parameterized constructor
    public Stock(int productID, int stockID, int stockQuantity, Date stockDate, boolean stockAvailable) {
        this.productID = productID;
        this.stockID = stockID;
        this.stockQuantity = stockQuantity;
        this.stockDate = stockDate;
        this.stockAvailable = stockAvailable;
    }

    // Getters and setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public boolean isStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(boolean stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Stock{" +
                "productID=" + productID +
                ", stockID=" + stockID +
                ", stockQuantity=" + stockQuantity +
                ", stockDate=" + stockDate +
                ", stockAvailable=" + stockAvailable +
                '}';
    }
}
