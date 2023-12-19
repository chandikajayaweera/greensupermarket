package com.greensupermarket.model;

public class SubCategory {

    private String categoryName;
    private String subCategoryName;

    // Default constructor
    public SubCategory() {
    }

    // Parameterized constructor
    public SubCategory(String categoryName, String subCategoryName) {
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
    }

    // Getters and setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }


    // toString method for debugging or logging
    @Override
    public String toString() {
        return "SubCategory{" +
                "categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                '}';
    }
}
