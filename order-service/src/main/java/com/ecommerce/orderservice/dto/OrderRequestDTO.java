package com.ecommerce.orderservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    private Long customerId;
    private Long productId;
    private Integer quantity;
}
