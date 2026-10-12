package com.ecommerce.customerservice.service.impl;

import com.ecommerce.customerservice.dto.CustomerRequestDTO;
import com.ecommerce.customerservice.dto.CustomerResponseDTO;
import com.ecommerce.customerservice.dto.LoginRequestDTO;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.exception.ResourceNotFoundException;
import com.ecommerce.customerservice.repository.CustomerRepository;
import com.ecommerce.customerservice.service.CustomerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public CustomerResponseDTO register(CustomerRequestDTO requestDTO) {
        Customer customer = Customer.builder()
                .fullName(requestDTO.getFullName())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return mapToResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        return mapToResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Customer customer = customerRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("email or password incorrect"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), customer.getPassword())) {
            throw new IllegalArgumentException("email or password incorrect");
        }

        return mapToResponseDTO(customer);
    }

    private CustomerResponseDTO mapToResponseDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .build();
    }
}
