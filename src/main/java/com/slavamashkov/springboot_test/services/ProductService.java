package com.slavamashkov.springboot_test.services;

import com.slavamashkov.springboot_test.entities.Product;
import com.slavamashkov.springboot_test.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

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

    public List<Product> getProductWithFilter(Specification<Product> productSpecification) {
        if (productSpecification == null) {
            return getAllProducts();
        } else {
            return productRepository.findAll(productSpecification);
        }
    }

    public void incrementViewsCounter(Product product) {
        product.setViews(product.getViews() + 1);
        add(product);
    }

    public Page<Product> getMostViewedProducts() {
        return productRepository.getMostViewedProducts(PageRequest.of(0, 3));
    }
}
