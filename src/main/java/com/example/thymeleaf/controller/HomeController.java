package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/index.html"})
    public String home(Model model) {
        model.addAttribute("welcome", "Hello, Thymeleaf!");
        return "index";
    }
}
