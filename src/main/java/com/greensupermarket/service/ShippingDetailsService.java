package com.greensupermarket.service;

import com.greensupermarket.dao.ShippingDetailsDAO;
import com.greensupermarket.model.ShippingDetails;

import java.util.List;

public class ShippingDetailsService {

    private final ShippingDetailsDAO shippingDetailsDAO;

    public ShippingDetailsService() {
        this.shippingDetailsDAO = new ShippingDetailsDAO();
    }

    // Create shipping details for a customer order
    public boolean createShippingDetails(ShippingDetails shippingDetails, int customerOrderID) {
        return shippingDetailsDAO.createShippingDetails(shippingDetails, customerOrderID);
    }

    // Retrieve shipping details by CustomerOrderID
    public List<ShippingDetails> getShippingDetailsByCustomerOrderID(int customerOrderID) {
        return shippingDetailsDAO.getShippingDetailsByCustomerOrderID(customerOrderID);
    }
}
