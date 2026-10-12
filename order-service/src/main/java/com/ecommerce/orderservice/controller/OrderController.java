package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.dto.OrderResponseDTO;
import com.ecommerce.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/v1/orders - Tạo đơn hàng mới
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO requestDTO) {
        OrderResponseDTO responseDTO = orderService.createOrder(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // GET /api/v1/orders/{id} - Lấy thông tin đơn hàng
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        OrderResponseDTO responseDTO = orderService.getOrderById(id);
        return ResponseEntity.ok(responseDTO);
    }
}
