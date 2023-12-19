package com.greensupermarket.model;

import java.util.Date;

public class Feedback {

    private int customerID;
    private int feedbackID;
    private int feedbackRating;
    private String feedbackMessage;
    private Date feedbackDate;

    // Default constructor
    public Feedback() {
    }

    // Parameterized constructor
    public Feedback(int customerID, int feedbackID, int feedbackRating, String feedbackMessage, Date feedbackDate) {
        this.customerID = customerID;
        this.feedbackID = feedbackID;
        this.feedbackRating = feedbackRating;
        this.feedbackMessage = feedbackMessage;
        this.feedbackDate = feedbackDate;
    }

    // Getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(int feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Feedback{" +
                "customerID=" + customerID +
                ", feedbackID=" + feedbackID +
                ", feedbackRating=" + feedbackRating +
                ", feedbackMessage='" + feedbackMessage + '\'' +
                ", feedbackDate=" + feedbackDate +
                '}';
    }
}
