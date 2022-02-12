package com.slavamashkov.springboot_test.repositories;

import com.slavamashkov.springboot_test.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
