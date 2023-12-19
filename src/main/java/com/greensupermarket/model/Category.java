package com.greensupermarket.model;

public class Category {

    private String categoryName;
    private String categoryImageURL;

    // Default constructor
    public Category() {
    }

    // Parameterized constructor
    public Category(String categoryName, String categoryImageURL) {
        this.categoryName = categoryName;
        this.categoryImageURL = categoryImageURL;
    }

    // Getters and setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageURL() {
        return categoryImageURL;
    }

    public void setCategoryImageURL(String categoryImageURL) {
        this.categoryImageURL = categoryImageURL;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryImageURL='" + categoryImageURL + '\'' +
                '}';
    }
}
