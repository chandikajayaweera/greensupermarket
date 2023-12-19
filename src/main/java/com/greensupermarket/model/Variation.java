package com.greensupermarket.model;

public class Variation {

    private String variationName;
    private String variationDescription;

    // Default constructor
    public Variation() {
    }

    // Parameterized constructor
    public Variation(String variationName, String variationDescription) {
        this.variationName = variationName;
        this.variationDescription = variationDescription;
    }

    // Getters and setters
    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public String getVariationDescription() {
        return variationDescription;
    }

    public void setVariationDescription(String variationDescription) {
        this.variationDescription = variationDescription;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Variation{" +
                "variationName='" + variationName + '\'' +
                ", variationDescription='" + variationDescription + '\'' +
                '}';
    }
}
