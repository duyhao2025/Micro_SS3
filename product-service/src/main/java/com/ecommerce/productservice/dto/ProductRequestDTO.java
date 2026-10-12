package com.ecommerce.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

    @NotBlank(message = "Product name must not be blank")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private Double price;

    @Min(value = 0, message = "Stock quantity must not be negative")
    private Integer stockQuantity;
}
