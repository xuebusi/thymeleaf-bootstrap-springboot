package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping({"", "/index.html"})
    public String home(Model model) {
        model.addAttribute("welcome", "Hello, Thymeleaf!");
        return "index";
    }
}
