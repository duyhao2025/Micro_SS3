package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductRequestDTO;
import com.ecommerce.productservice.dto.ProductResponseDTO;
import com.ecommerce.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // POST /api/v1/products - Tạo sản phẩm mới (Có kiểm tra Validation)
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO responseDTO = productService.createProduct(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // GET /api/v1/products/{id} - Lấy chi tiết sản phẩm
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO responseDTO = productService.getProductById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // GET /api/v1/products - Lấy danh sách toàn bộ sản phẩm
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
