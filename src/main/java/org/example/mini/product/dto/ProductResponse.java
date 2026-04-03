package org.example.mini.product.dto;

import org.example.mini.product.Product;

public record ProductResponse(String name, Integer price) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getName(), product.getPrice());
    }
}