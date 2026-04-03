package org.example.mini.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    public static Product createProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.name = name;
        product.price = price;
        product.stock = stock;
        return product;
    }

    public void updateProduct(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void decreaseStock() {
        if (stock <= 0) {
            throw new IllegalStateException("No stock");
        }
        this.stock--;
    }
}
