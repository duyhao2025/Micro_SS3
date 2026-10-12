package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.dto.CustomerRequestDTO;
import com.ecommerce.customerservice.dto.CustomerResponseDTO;
import com.ecommerce.customerservice.dto.LoginRequestDTO;

public interface CustomerService {

    CustomerResponseDTO register(CustomerRequestDTO requestDTO);

    CustomerResponseDTO getCustomerById(Long id);

    CustomerResponseDTO login(LoginRequestDTO loginRequestDTO);
}
