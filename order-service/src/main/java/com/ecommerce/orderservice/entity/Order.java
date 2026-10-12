package com.ecommerce.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Chỉ lưu customerId dưới dạng Long - KHÔNG dùng @ManyToOne vì Customer nằm ở DB khác
    @Column(nullable = false)
    private Long customerId;

    // Chỉ lưu productId dưới dạng Long - KHÔNG dùng @ManyToOne vì Product nằm ở DB khác
    @Column(nullable = false)
    private Long productId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Double totalAmount;
}
