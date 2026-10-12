package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.dto.ProductRequestDTO;
import com.ecommerce.productservice.dto.ProductResponseDTO;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.exception.ResourceNotFoundException;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = Product.builder()
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .stockQuantity(requestDTO.getStockQuantity())
                .build();

        Product savedProduct = productRepository.save(product);

        return mapToResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        return mapToResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }
}
