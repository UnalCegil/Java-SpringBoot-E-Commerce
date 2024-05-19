package com.ecommerce.services.product;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO);
    boolean deleteProduct(Long id);
    boolean checkStockAvailability(Long productId, int quantity);
}
