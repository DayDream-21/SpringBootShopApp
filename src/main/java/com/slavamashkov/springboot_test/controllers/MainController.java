package com.slavamashkov.springboot_test.controllers;

import com.slavamashkov.springboot_test.Cat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class MainController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/getCat")
    @ResponseBody
    public Cat showCat() {
        return new Cat(1L, "Barsik");
    }
}
