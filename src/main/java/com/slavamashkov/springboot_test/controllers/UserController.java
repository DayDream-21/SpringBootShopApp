package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.entities.User;
import com.slavamashkov.springboot_test.exceptions.UserAlreadyExistsException;
import com.slavamashkov.springboot_test.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class UserController {
    private final UserService userService;

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
    public String registerUser(Model model,
                               @ModelAttribute(value = "user") @Valid User user,
                               BindingResult bindingResult) {
        try {
            userService.registerUser(user);
        } catch (UserAlreadyExistsException exception) {
            model.addAttribute("error_message", "An account for that username/email already exists.");
            return "registration-page";
        }

        if (bindingResult.hasErrors()) {
            return "registration-page";
        }

        return "redirect:/authentication/login";
    }
}
