package com.ecommerce.productservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private Integer stockQuantity;
}
