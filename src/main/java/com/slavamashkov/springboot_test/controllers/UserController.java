package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.entities.User;
import com.slavamashkov.springboot_test.repositories.RoleRepository;
import com.slavamashkov.springboot_test.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class UserController {
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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

        return "redirect:/authentication/login";
    }
}
