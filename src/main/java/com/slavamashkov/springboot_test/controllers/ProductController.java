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
    public String showAddProductPage(Model model) {
        Product product = new Product();

        model.addAttribute("product", product);

        return "add-product-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);

        model.addAttribute("product", product);
        productService.delete(product);

        return "redirect:/product";
    }

    @PostMapping("/addProduct")
    public String saveNewProduct(@ModelAttribute(value = "product") Product product) {
        productService.add(product);

        return "redirect:/product";
    }

        return "single-product-page";
    }
}
