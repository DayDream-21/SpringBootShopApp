package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.entities.Product;
import com.slavamashkov.springboot_test.services.ProductService;
import com.slavamashkov.springboot_test.specifications.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "custom-login";
    }

    @GetMapping
    public String showProductsList(Model model,
                                   @RequestParam(value = "word", required = false) String word,
                                   @RequestParam(value = "min", required = false) Integer min,
                                   @RequestParam(value = "max", required = false) Integer max) {
        Specification<Product> specification = Specification.where(null);

        if (word != null) {
            specification = specification.and(ProductSpecs.titleContains(word));
        }
        if (min != null) {
            specification = specification.and(ProductSpecs.priceGreaterThenOrEqualTo(min));
        }
        if (max != null) {
            specification = specification.and(ProductSpecs.priceLessThenOrEqualTo(max));
        }

        model.addAttribute("products", productService.getProductWithFilter(specification));

        model.addAttribute("word", word);
        model.addAttribute("min", min);
        model.addAttribute("max", max);

        return "products-page";
    }

    @GetMapping("/singleProduct/{id}")
    public String showSingleProductPage(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productService.getById(id));

        return "single-product-page";
    }

    @GetMapping("/{page}")
    public String showProductPage(Model model, @PathVariable(value = "page") Integer page) {
        Pageable pageWithFiveElements = PageRequest.of(page, 5);
        List<Product> fiveProductsOnPage = productService
                .getAllProductsPageable(pageWithFiveElements)
                .getContent();

        model.addAttribute("products", fiveProductsOnPage);

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
        productService.deleteById(id);

        return "redirect:/product";
    }

    @PostMapping("/update")
    public String saveChanges(@ModelAttribute(value = "product") Product product) {
        productService.add(product);

        return "redirect:/product";
    }
}
