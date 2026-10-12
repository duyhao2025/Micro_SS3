package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductRequestDTO;
import com.ecommerce.productservice.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getAllProducts();
}
