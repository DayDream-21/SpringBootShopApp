package com.slavamashkov.springboot_test.entities;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Long id;
    private String title;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && id.equals(product.id) && title.equals(product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price);
    }
}
