package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.Cat;
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

    @GetMapping("/getCat")
    @ResponseBody
    public Cat showCat() {
        return new Cat(1L, "Barsik");
    }
}
