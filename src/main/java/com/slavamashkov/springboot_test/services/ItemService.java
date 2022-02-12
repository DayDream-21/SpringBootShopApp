package com.slavamashkov.springboot_test.services;

import com.slavamashkov.springboot_test.entities.Item;
import com.slavamashkov.springboot_test.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems()  {
        return itemRepository.findAll();
    }
}
