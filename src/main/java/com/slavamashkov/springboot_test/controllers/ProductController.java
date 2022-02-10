package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.entities.Product;
import com.slavamashkov.springboot_test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductsList(Model model) {
        Product product = new Product();

        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", product);

        return "products-page";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();

        model.addAttribute("product", product);

        return "edit-product-page";
    }

    @GetMapping("/updateProduct/{id}")
    public String updateProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);

        model.addAttribute("product", product);

        return "edit-product-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);

        productService.delete(product);

        return "redirect:/product";
    }

    @PostMapping("/update")
    public String saveChanges(@ModelAttribute(value = "product") Product product) {
        productService.add(product);

        return "redirect:/product";
    }
}
