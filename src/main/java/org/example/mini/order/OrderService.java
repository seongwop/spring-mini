package org.example.mini.order;

import lombok.RequiredArgsConstructor;
import org.example.mini.order.dto.OrderCreateRequest;
import org.example.mini.order.dto.OrderResponse;
import org.example.mini.product.Product;
import org.example.mini.product.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        Long productId = orderCreateRequest.productId();
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("product not found")
        );
        Order order = Order.createOrder(product);

        orderRepository.save(order);

        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<OrderResponse> getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("order not found")
        );
        return ResponseEntity.ok(OrderResponse.from(order));
    }


    public ResponseEntity<List<OrderResponse>> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<OrderResponse> orderResponseList = orderRepository.findAll(pageable).stream().map(OrderResponse::from).toList();
        return ResponseEntity.ok(orderResponseList);
    }
}
