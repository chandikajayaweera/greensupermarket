package com.greensupermarket.model;

import java.util.Date;

public class Support {

    private int supportID;
    private Date supportDate;
    private String supportName;
    private String supportEmail;
    private String supportMessage;

    // Default constructor
    public Support() {
    }

    // Parameterized constructor
    public Support(int supportID, Date supportDate, String supportName, String supportEmail, String supportMessage) {
        this.supportID = supportID;
        this.supportDate = supportDate;
        this.supportName = supportName;
        this.supportEmail = supportEmail;
        this.supportMessage = supportMessage;
    }

    // Getters and setters
    public int getSupportID() {
        return supportID;
    }

    public void setSupportID(int supportID) {
        this.supportID = supportID;
    }

    public Date getSupportDate() {
        return supportDate;
    }

    public void setSupportDate(Date supportDate) {
        this.supportDate = supportDate;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportMessage() {
        return supportMessage;
    }

    public void setSupportMessage(String supportMessage) {
        this.supportMessage = supportMessage;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Support{" +
                "supportID=" + supportID +
                ", supportDate=" + supportDate +
                ", supportName='" + supportName + '\'' +
                ", supportEmail='" + supportEmail + '\'' +
                ", supportMessage='" + supportMessage + '\'' +
                '}';
    }
}
