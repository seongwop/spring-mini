package org.example.mini.order.dto;

import org.example.mini.order.Order;

public record OrderResponse(String productName) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getProduct().getName());
    }
}
