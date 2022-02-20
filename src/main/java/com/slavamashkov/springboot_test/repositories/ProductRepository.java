package com.slavamashkov.springboot_test.repositories;

import com.slavamashkov.springboot_test.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query("SELECT p FROM Product p ORDER BY p.views DESC")
    Page<Product> getMostViewedProducts(Pageable pageable);

    void deleteProductByTitle(String title);
}
