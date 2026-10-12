package com.ecommerce.customerservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    // Không bao gồm password để bảo mật
}
