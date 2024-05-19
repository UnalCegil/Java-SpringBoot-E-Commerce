package com.ecommerce.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.services.auth.AuthService;
import com.ecommerce.services.product.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthService authService;
    
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {

        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(ProductMapper.toProductDTO(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{userId}")
    public ResponseEntity<ProductDTO> createProduct(@PathVariable Long userId,@RequestBody ProductDTO productDTO) {
    	
        Customer user = authService.getCustomerById(userId);
        
        if (user == null || !user.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Product product = productService.createProduct(productDTO);
        return ResponseEntity.ok(ProductMapper.toProductDTO(product));
    }

    @PutMapping("/{id}/{userId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@PathVariable Long userId, @RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(id, productDTO);
        if (product != null) {
            return ResponseEntity.ok(ProductMapper.toProductDTO(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id,@PathVariable Long userId) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

