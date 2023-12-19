package com.greensupermarket.model;

public class VariationValue {

    private String variationName;
    private String variationValueName;

    // Default constructor
    public VariationValue() {
    }

    // Parameterized constructor
    public VariationValue(String variationName, String variationValueName) {
        this.variationName = variationName;
        this.variationValueName = variationValueName;
    }

    // Getters and setters
    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public String getVariationValueName() {
        return variationValueName;
    }

    public void setVariationValueName(String variationValueName) {
        this.variationValueName = variationValueName;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "VariationValue{" +
                "variationName='" + variationName + '\'' +
                ", variationValueName='" + variationValueName + '\'' +
                '}';
    }
}
