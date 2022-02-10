package com.slavamashkov.springboot_test.repositories;

import com.slavamashkov.springboot_test.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Imagine that is repository that connects to DB
 */

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle(String title) {
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getTitle().equals(title))
                .findFirst();

        return optionalProduct.orElseGet(Product::new);
    }

    public Product findById(Long id) {
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();

        return optionalProduct.orElseGet(Product::new);
    }

    public void save(Product product) {
        if (product.getId() == null) {
            Long newId = products.stream()
                    .mapToLong(Product::getId)
                    .max()
                    .getAsLong() + 1;
            product.setId(newId);
            products.add(product);
        } else {
            for (Product p : products) {
                if (p.getId().equals(product.getId())) {
                    p.setTitle(product.getTitle());
                    p.setPrice(product.getPrice());
                    return;
                }
            }
        }
    }

    public void delete(Product product) {
        products.remove(product);
    }
 }
