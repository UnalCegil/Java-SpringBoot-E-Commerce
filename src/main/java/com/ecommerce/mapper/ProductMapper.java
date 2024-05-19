package com.ecommerce.mapper;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entities.Product;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        return dto;
    }

    public static Product toProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        return product;
    }
}

