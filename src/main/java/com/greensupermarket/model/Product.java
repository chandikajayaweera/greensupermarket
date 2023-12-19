package com.greensupermarket.model;

public class Product {

    private int productID;
    private String unitName;
    private String brandName;
    private String variationValueName;
    private String subCategoryName;
    private String productSKU;
    private String productName;
    private String productDescription;
    private double productUnitPrice;
    private boolean productIsDiscounted;
    private double productDiscountedPrice;
    private boolean productIsActive;

    // Default constructor
    public Product() {
    }

    // Parameterized constructor
    public Product(
            int productID,
            String unitName,
            String brandName,
            String variationValueName,
            String subCategoryName,
            String productSKU,
            String productName,
            String productDescription,
            double productUnitPrice,
            boolean productIsDiscounted,
            double productDiscountedPrice,
            boolean productIsActive
    ) {
        this.productID = productID;
        this.unitName = unitName;
        this.brandName = brandName;
        this.variationValueName = variationValueName;
        this.subCategoryName = subCategoryName;
        this.productSKU = productSKU;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productUnitPrice = productUnitPrice;
        this.productIsDiscounted = productIsDiscounted;
        this.productDiscountedPrice = productDiscountedPrice;
        this.productIsActive = productIsActive;
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

    public String getVariationValueName() {
        return variationValueName;
    }

    public void setVariationValueName(String variationValueName) {
        this.variationValueName = variationValueName;
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

    public boolean isProductIsDiscounted() {
        return productIsDiscounted;
    }

    public void setProductIsDiscounted(boolean productIsDiscounted) {
        this.productIsDiscounted = productIsDiscounted;
    }

    public double getProductDiscountedPrice() {
        return productDiscountedPrice;
    }

    public void setProductDiscountedPrice(double productDiscountedPrice) {
        this.productDiscountedPrice = productDiscountedPrice;
    }

    public boolean isProductIsActive() {
        return productIsActive;
    }

    public void setProductIsActive(boolean productIsActive) {
        this.productIsActive = productIsActive;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", unitName='" + unitName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", variationValueName='" + variationValueName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", productSKU='" + productSKU + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productUnitPrice=" + productUnitPrice +
                ", productIsDiscounted=" + productIsDiscounted +
                ", productDiscountedPrice=" + productDiscountedPrice +
                ", productIsActive=" + productIsActive +
                '}';
    }
}
