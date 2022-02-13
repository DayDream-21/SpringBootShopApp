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

    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteByTitle(String title) {
        productRepository.deleteProductByTitle(title);
    }

    public void add(Product product) {
        productRepository.save(product);
    }
}
