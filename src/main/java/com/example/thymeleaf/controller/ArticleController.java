package com.example.thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.thymeleaf.model.Article;
import com.example.thymeleaf.service.ArticleService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping({"", "/", "index.html"})
    public String index(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        Page<Article> articlePage = articleService.getArticles(page, size, keyword);

        model.addAttribute("articles", articlePage.getRecords());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", articlePage.getPages());
        model.addAttribute("keyword", keyword);

        return "/article/index";
    }

    @GetMapping({"/{articleId}", "/{articleId}/"})
    public String detail(@PathVariable("articleId") Long articleId, Model model) {
        Article article = articleService.getById(articleId);
        model.addAttribute("article", article);
        return "/article/detail";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        model.addAttribute("article", new Article());
        return "/article/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Article article, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("article", article);
            return "/article/create";
        }
        articleService.save(article);
        return "redirect:/article";
    }

    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable("id") Long id, Model model) {
        Article article = articleService.getById(id);
        model.addAttribute("article", article);
        return "/article/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute Article article, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            article.setArticleId(id);
            model.addAttribute("article", article);
            return "/article/edit";
        }
        article.setArticleId(id);
        articleService.updateById(article);
        return "redirect:/article";
    }
}