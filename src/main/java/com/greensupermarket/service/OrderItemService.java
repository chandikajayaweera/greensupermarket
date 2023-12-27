package com.greensupermarket.service;

import com.greensupermarket.dao.OrderItemDAO;
import com.greensupermarket.model.OrderItem;

import java.util.List;

public class OrderItemService {

    private final OrderItemDAO orderItemDAO;

    public OrderItemService() {
        this.orderItemDAO = new OrderItemDAO();
    }

    public boolean addOrderItem(OrderItem orderItem) {
        return orderItemDAO.addOrderItem(orderItem);
    }

    public OrderItem getOrderItemById(int customerOrderID, int productID) {
        return orderItemDAO.getOrderItemById(customerOrderID, productID);
    }

    public boolean updateOrderItem(OrderItem orderItem) {
        return orderItemDAO.updateOrderItem(orderItem);
    }

    public boolean deleteOrderItem(int customerOrderID, int productID) {
        return orderItemDAO.deleteOrderItem(customerOrderID, productID);
    }

    public List<OrderItem> getAllOrderItemsByOrderId(int customerOrderID) {
        return orderItemDAO.getAllOrderItemsByOrderId(customerOrderID);
    }
    
    public List<OrderItem> getAllOrderItems(){
        return orderItemDAO.getAllOrderItems();
    }
}
