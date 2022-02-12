package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {
    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String showItemsPage(Model model) {
        model.addAttribute("items", itemService.getAllItems());

        return "items";
    }
}
