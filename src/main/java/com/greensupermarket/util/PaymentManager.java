package com.greensupermarket.util;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PaymentManager {

    public APIContext getAPIContext() {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream("config/paypal.properties");
            properties.load(inputStream);

            String CLIENT_ID = properties.getProperty("paypal.clientId");
            String CLIENT_SECRET = properties.getProperty("paypal.clientSecret");
            String MODE = properties.getProperty("paypal.mode");

            APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
            return apiContext;
        } catch (IOException e) {
            System.out.println("Error loading PayPal properties file: " + e.getMessage());
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Error closing input stream: " + e.getMessage());
                }
            }
        }
    }

    public Payment getPaymentDetails(String paymentId) {
        try {
            APIContext apiContext = getAPIContext();
            if (apiContext != null) {
                return Payment.get(apiContext, paymentId);
            } else {
                System.out.println("APIContext is null. Unable to retrieve payment details.");
                return null;
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error getting payment details: " + e.getMessage());
            return null;
        }
    }
}
