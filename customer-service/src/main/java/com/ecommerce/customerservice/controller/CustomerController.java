package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.dto.CustomerRequestDTO;
import com.ecommerce.customerservice.dto.CustomerResponseDTO;
import com.ecommerce.customerservice.dto.LoginRequestDTO;
import com.ecommerce.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // POST /api/v1/customers/register
    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> register(@RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO responseDTO = customerService.register(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // GET /api/v1/customers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        CustomerResponseDTO responseDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // PUT /api/v1/customers/login
    @PutMapping("/login")
    public ResponseEntity<CustomerResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        CustomerResponseDTO responseDTO = customerService.login(loginRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
