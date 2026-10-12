package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.dto.OrderResponseDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.exception.ResourceNotFoundException;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // Giả lập giá sản phẩm (trong thực tế sẽ gọi API sang Product Service)
    private static final Double SIMULATED_PRODUCT_PRICE = 100.0;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {
        // Validate quantity > 0
        if (requestDTO.getQuantity() == null || requestDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        // Tính totalAmount (giả lập giá lấy từ Product Service)
        Double totalAmount = SIMULATED_PRODUCT_PRICE * requestDTO.getQuantity();

        Order order = Order.builder()
                .customerId(requestDTO.getCustomerId())
                .productId(requestDTO.getProductId())
                .orderDate(LocalDateTime.now())
                .totalAmount(totalAmount)
                .build();

        Order savedOrder = orderRepository.save(order);

        return mapToResponseDTO(savedOrder);
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        return mapToResponseDTO(order);
    }

    private OrderResponseDTO mapToResponseDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .build();
    }
}
