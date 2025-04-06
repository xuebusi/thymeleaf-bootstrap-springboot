package com.example.thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.thymeleaf.model.Product;
import com.example.thymeleaf.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("product", new Product());
        return "/product/create";
    }

    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable("id") Long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "/product/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            product.setProductId(id);
            model.addAttribute("product", product);
            return "/product/edit";
        }
        product.setProductId(id);
        productService.updateById(product);
        return "redirect:/product";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            return "/product/create";
        }
        productService.save(product);
        return "redirect:/product";
    }

    // 添加商品详情页处理方法
    @GetMapping("/{productId}")
    public String detail(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.getById(productId);
        model.addAttribute("product", product);
        return "/product/detail";
    }
}