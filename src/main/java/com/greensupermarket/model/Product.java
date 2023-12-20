package com.greensupermarket.model;

public class Product {

    private int productID;
    private String unitName;
    private String brandName;
    private String subCategoryName;
    private String productSKU;
    private String productName;
    private String productDescription;
    private double productUnitPrice;
    private int productStock;
    private String productImageURL;

    // Default constructor
    public Product() {
    }

    // Parameterized constructor
    public Product(
            int productID,
            String unitName,
            String brandName,
            String subCategoryName,
            String productSKU,
            String productName,
            String productDescription,
            double productUnitPrice,
            int productStock,
            String productImageURL
            
    ) {
        this.productID = productID;
        this.unitName = unitName;
        this.brandName = brandName;
        this.subCategoryName = subCategoryName;
        this.productSKU = productSKU;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productUnitPrice = productUnitPrice;
        this.productStock = productStock;
        this.productImageURL = productImageURL;
    }

    // Getters and setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getProductSKU() {
        return productSKU;
    }

    public void setProductSKU(String productSKU) {
        this.productSKU = productSKU;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }
    
    public int getProductStock(){
        return productStock;
    }
    
    public void setProductStock(int productStock){
        this.productStock = productStock;
    }
    
    public String getProductImageURL(){
        return productImageURL;
    }
    
    public void setProductImageURL(String productImageURL){
        this.productImageURL = productImageURL;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", unitName='" + unitName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", productSKU='" + productSKU + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productUnitPrice=" + productUnitPrice + '\'' +
                ", productStock=" + productStock + '\'' +
                ", productImageURL=" + productImageURL +
                '}';
    }
}
