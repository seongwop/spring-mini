package org.example.mini.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
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

    public static Product createProduct(String name, Integer price){
        Product product = new Product();
        product.name = name;
        product.price = price;
        return product;
    }

    public void updateProduct(String name, Integer price){
        this.name = name;
        this.price = price;
    }
}
