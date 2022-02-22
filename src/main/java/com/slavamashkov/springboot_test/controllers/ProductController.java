package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.entities.Product;
import com.slavamashkov.springboot_test.entities.User;
import com.slavamashkov.springboot_test.repositories.RoleRepository;
import com.slavamashkov.springboot_test.services.ProductService;
import com.slavamashkov.springboot_test.services.UserServiceImpl;
import com.slavamashkov.springboot_test.specifications.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private UserServiceImpl userService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "custom-login";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        User emptyUser = new User();
        model.addAttribute("user", emptyUser);

        return "registration-page";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute(value = "user") User user) {
        User newUser = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .email(user.getEmail())
                .enabled(Boolean.TRUE)
                .roles(roleRepository.findAllById(List.of(1L)))
                .build();

        userService.saveUser(newUser);

        return "redirect:/product/login";
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

        model.addAttribute("top3List", productService.getMostViewedProducts());

        return "products-page";
    }

    @GetMapping("/singleProduct/{id}")
    public String showSingleProductPage(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);

        productService.incrementViewsCounter(product);

        model.addAttribute("product", product);

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
