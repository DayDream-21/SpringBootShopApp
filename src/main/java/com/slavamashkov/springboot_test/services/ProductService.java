package com.slavamashkov.springboot_test.services;

import com.slavamashkov.springboot_test.entities.Product;
import com.slavamashkov.springboot_test.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }
}
