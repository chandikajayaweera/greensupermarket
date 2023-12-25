package com.greensupermarket.service;

import com.greensupermarket.dao.ProductDAO;
import com.greensupermarket.model.Cart;
import com.greensupermarket.model.OrderItem;
import com.greensupermarket.model.Product;

import java.util.List;

public class CartService {

    private final ProductDAO productDao;

    public CartService() {
        this.productDao = new ProductDAO();
    }

    public void addToCart(Cart cart, int productID, int quantity) {
        Product product = productDao.getProductById(productID);
        if (product != null) {
            // Check if the product is already in the cart
            OrderItem existingItem = findOrderItemInCart(cart, productID);

            if (existingItem != null) {

                if ((existingItem.getOrderItemQuantity() + quantity) > product.getProductStock()) {
                    throw new IllegalArgumentException("Quantity exceeds available stock");
                }
                
                existingItem.setOrderItemQuantity(existingItem.getOrderItemQuantity() + quantity);

            } else {
                
                OrderItem orderItem = new OrderItem();
                orderItem.setProductID(product.getProductID());
                orderItem.setProductName(product.getProductName());
                orderItem.setProductImageURL(product.getProductImageURL());
                orderItem.setOrderItemUnitPrice(product.getProductUnitPrice());
                orderItem.setOrderItemQuantity(quantity);
                cart.addItem(orderItem);
            }
        }
    }

    // Helper method to find an OrderItem in the cart by product ID
    private OrderItem findOrderItemInCart(Cart cart, int productID) {
        return cart.getOrderItems()
                .stream()
                .filter(item -> item.getProductID() == productID)
                .findFirst()
                .orElse(null);
    }
}
