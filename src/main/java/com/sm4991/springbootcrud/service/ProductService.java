package com.sm4991.springbootcrud.service;

import com.sm4991.springbootcrud.dto.ProductDto;
import com.sm4991.springbootcrud.entity.Product;
import com.sm4991.springbootcrud.enums.UserRole;
import com.sm4991.springbootcrud.repository.ProductRepository;
import com.sm4991.springbootcrud.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Create a new product
     * @param productDto
     * @return
     */
    public Pair<Boolean, Object> createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setAmount(productDto.getAmount());
        product.setCreatedAt(DateUtil.getCurrentTimestamp());
        product.setUpdatedAt(DateUtil.getCurrentTimestamp());
        product = productRepository.save(product);
        if(product.getId() != null) {
            return Pair.of(true, product);
        } else {
            return Pair.of(false, "Product could not be saved");
        }
    }

    /**
     * Update existing product
     * @param productDto
     * @return
     */
    public Pair<Boolean, Object> updateProduct(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setAmount(productDto.getAmount());
        product.setUpdatedAt(DateUtil.getCurrentTimestamp());
        product = productRepository.save(product);
        return Pair.of(true, product);
    }

    /**
     * Fetch all product entities
     * @return
     */
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * Fetch product entity by id
     * @param id
     * @return
     */
    public Optional<Product> getById(UUID id) {
        return productRepository.findById(id);
    }

    /**
     * Fetch product entity by id
     * @param id
     * @return
     */
    public Boolean deleteById(UUID id) {
        productRepository.deleteById(id);
        return true;
    }
}
