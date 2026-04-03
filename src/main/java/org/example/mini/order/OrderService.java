package org.example.mini.order;

import lombok.RequiredArgsConstructor;
import org.example.mini.order.dto.OrderCreateRequest;
import org.example.mini.order.dto.OrderResponse;
import org.example.mini.product.Product;
import org.example.mini.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        Long productId = orderCreateRequest.productId();
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("product not found")
        );
        Order order = Order.createOrder(product);

        orderRepository.save(order);

        return OrderResponse.from(order);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("order not found")
        );
        return OrderResponse.from(order);
    }
}
