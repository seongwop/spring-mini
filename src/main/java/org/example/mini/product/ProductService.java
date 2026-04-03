package org.example.mini.product;

import lombok.RequiredArgsConstructor;
import org.example.mini.product.dto.ProductCreateRequest;
import org.example.mini.product.dto.ProductResponse;
import org.example.mini.product.dto.ProductUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {
        String name = productCreateRequest.name();
        Integer price = productCreateRequest.price();
        Product product = Product.createProduct(name,price);

        productRepository.save(product);

        return ProductResponse.from(product);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product not found with id " + id)
        );
        return ProductResponse.from(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponse::from).toList();
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {
        String name = productUpdateRequest.name();
        Integer price = productUpdateRequest.price();

        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product not found with id " + id)
        );

        product.updateProduct(name, price);

        return ProductResponse.from(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product not found with id " + id)
        );
        productRepository.delete(product);
    }
}
