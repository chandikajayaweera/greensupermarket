package com.greensupermarket.model;

public class ProductImage {

    private int productID;
    private int productImageID;
    private String productImageURL;

    // Default constructor
    public ProductImage() {
    }

    // Parameterized constructor
    public ProductImage(int productID, int productImageID, String productImageURL) {
        this.productID = productID;
        this.productImageID = productImageID;
        this.productImageURL = productImageURL;
    }

    // Getters and setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(int productImageID) {
        this.productImageID = productImageID;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "ProductImage{" +
                "productID=" + productID +
                ", productImageID=" + productImageID +
                ", productImageURL='" + productImageURL + '\'' +
                '}';
    }
}
