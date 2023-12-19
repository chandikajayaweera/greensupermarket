package com.greensupermarket.model;

public class Brand {

    private String brandName;
    private String brandLogoURL;

    // Default constructor
    public Brand() {
    }

    // Parameterized constructor
    public Brand(String brandName, String brandLogoURL) {
        this.brandName = brandName;
        this.brandLogoURL = brandLogoURL;
    }

    // Getters and setters
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogoURL() {
        return brandLogoURL;
    }

    public void setBrandLogoURL(String brandLogoURL) {
        this.brandLogoURL = brandLogoURL;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Brand{" +
                "brandName='" + brandName + '\'' +
                ", brandLogoURL='" + brandLogoURL + '\'' +
                '}';
    }
}
