package com.example.thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.thymeleaf.model.Product;
import com.example.thymeleaf.model.ProductVO;
import com.example.thymeleaf.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping({"", "index.html"})
    public String index(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        Page<Product> productPage = productService.getProducts(page, size, keyword);

        model.addAttribute("products", productPage.getRecords());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getPages());
        model.addAttribute("keyword", keyword);

        return "/product/index";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        model.addAttribute("vo", new ProductVO());
        return "/product/create";
    }

    @GetMapping("/edit")
    public String toEdit(Model model) {
        model.addAttribute("vo", new ProductVO());
        return "/product/edit";
    }
}
