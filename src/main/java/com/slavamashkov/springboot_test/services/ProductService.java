package com.slavamashkov.springboot_test.services;

import com.slavamashkov.springboot_test.entities.Product;
import com.slavamashkov.springboot_test.repositories.ProductRepository;
import com.slavamashkov.springboot_test.specifications.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
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

    public List<Product> getProductWithTitleFilter(String word) {
        Optional<String> optionalTitle = Optional.ofNullable(word);
        if (optionalTitle.isPresent()) {
            return productRepository.findAll(ProductSpecs.titleContains(word));
        } else {
            return getAllProducts();
        }
    }
}
