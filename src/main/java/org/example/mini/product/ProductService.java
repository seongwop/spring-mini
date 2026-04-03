package org.example.mini.product;

import lombok.RequiredArgsConstructor;
import org.example.mini.product.dto.ProductCreateRequest;
import org.example.mini.product.dto.ProductResponse;
import org.example.mini.product.dto.ProductUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ResponseEntity<ProductResponse> createProduct(ProductCreateRequest productCreateRequest) {
        String name = productCreateRequest.name();
        Integer price = productCreateRequest.price();
        Product product = Product.createProduct(name,price);

        productRepository.save(product);

        return ResponseEntity.ok(ProductResponse.from(product));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponse> getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product not found with id " + id)
        );
        return ResponseEntity.ok(ProductResponse.from(product));
    }

    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products.stream().map(ProductResponse::from).toList());
    }

    @Transactional
    public ResponseEntity<ProductResponse> updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {
        String name = productUpdateRequest.name();
        Integer price = productUpdateRequest.price();

        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product not found with id " + id)
        );

        product.updateProduct(name, price);

        return ResponseEntity.ok(ProductResponse.from(product));
    }

    @Transactional
    public ResponseEntity<?> deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product not found with id " + id)
        );
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
