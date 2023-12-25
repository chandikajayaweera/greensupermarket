package com.greensupermarket.model;

import com.greensupermarket.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderItem> orderItems;

    public Cart() {
        this.orderItems = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        orderItems.add(item);
    }

    public void removeItem(int productID) {
        orderItems.removeIf(item -> item.getProductID() == productID);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        return orderItems.stream().mapToDouble(item -> item.getOrderItemUnitPrice() * item.getOrderItemQuantity()).sum();
    }

    @Override
    public String toString() {
        return "Cart{" + "orderItems=" + orderItems + '}';
    }
}
