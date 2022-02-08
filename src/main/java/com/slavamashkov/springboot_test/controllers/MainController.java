package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.entities.Cat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class MainController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/hello/{helloName}")
    public String hello(Model model, @PathVariable(value = "helloName") String helloName)  {
        model.addAttribute("name", helloName);

        return "index";
    }

    @GetMapping("/form")
    public String showForm() {
        return "simple-form";
    }

    @PostMapping("/form")
    public String saveForm(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
        System.out.println(name);
        System.out.println(email);
        return "redirect:/main/index";
    }

    @GetMapping("/addCat")
    public String showAddCatForm(Model model) {
        Cat cat = new Cat(1L, "Floppa", "Orange");
        model.addAttribute("cat", cat);

        return "cat-form";
    }

    @PostMapping("/addCat")
    public String saveCatForm(@ModelAttribute(value = "cat") Cat cat) {
        System.out.println(cat);
        return "redirect:/main/index";
    }
}
