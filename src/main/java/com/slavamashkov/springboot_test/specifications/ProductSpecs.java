package com.slavamashkov.springboot_test.specifications;

import com.slavamashkov.springboot_test.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {
    public static Specification<Product> priceBetween(int min, int max) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), min, max);
    }

    public static Specification<Product> titleContains(String word) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }
}
