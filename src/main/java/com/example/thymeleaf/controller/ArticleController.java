package com.example.thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.thymeleaf.model.Article;
import com.example.thymeleaf.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping({"", "index.html"})
    public String index(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        Page<Article> articlePage = articleService.getProducts(page, size, keyword);

        model.addAttribute("articles", articlePage.getRecords());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", articlePage.getPages());
        model.addAttribute("keyword", keyword);

        return "/article/index";
    }
}
