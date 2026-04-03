package org.example.mini.order;

import lombok.RequiredArgsConstructor;
import org.example.mini.order.dto.OrderCreateRequest;
import org.example.mini.order.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderCreateRequest));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Page<OrderResponse>> getAllOrders(@RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        return ResponseEntity.ok(orderService.getAllOrders(page - 1, size));
    }
}
