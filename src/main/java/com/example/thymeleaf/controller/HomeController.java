package com.example.thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.thymeleaf.model.Article;
import com.example.thymeleaf.model.Product;
import com.example.thymeleaf.service.ArticleService;
import com.example.thymeleaf.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Resource
    private ProductService productService;
    @Resource
    private ArticleService articleService;

    @GetMapping({"", "/index.html"})
    public String home(Model model) {
        // 获取热门商品，假设取前3条
        Page<Product> productPage = productService.getProducts(1, 3, null);
        model.addAttribute("hotProducts", productPage.getRecords());

        // 获取热门文章，假设取前3条
        Page<Article> articlePage = articleService.getArticles(1, 3, null);
        model.addAttribute("hotArticles", articlePage.getRecords());

        model.addAttribute("welcome", "Hello, Thymeleaf!");
        return "index";
    }
}