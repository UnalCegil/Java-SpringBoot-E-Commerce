package com.ecommerce.services.product;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toProduct(productDTO);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
    	
        Optional<Product> existingProductOpt = productRepository.findById(id);
        
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setStockQuantity(productDTO.getStockQuantity());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean checkStockAvailability(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        return product != null && product.getStockQuantity() >= quantity;
    }
}
