package org.example.mini.order;

import lombok.RequiredArgsConstructor;
import org.example.mini.order.dto.OrderCreateRequest;
import org.example.mini.order.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        return orderService.createOrder(orderCreateRequest);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/get")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        return orderService.getAllOrders(page - 1, size);
    }
}
