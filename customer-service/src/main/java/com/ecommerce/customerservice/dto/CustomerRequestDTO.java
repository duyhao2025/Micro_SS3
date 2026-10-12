package com.ecommerce.customerservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {

    private String fullName;
    private String email;
    private String password;
}
