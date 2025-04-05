package com.example.thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.thymeleaf.model.Product;
import com.example.thymeleaf.model.ProductVO;
import com.example.thymeleaf.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("vo", new ProductVO());
        return "/product/create";
    }

    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable("id") String id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("productId", product.getProductId());

        ProductVO vo = new ProductVO();
        vo.setProductName(product.getProductName());
        vo.setProductPrice(product.getProductPrice());
        vo.setProductStock(product.getProductStock());

        model.addAttribute("vo", vo);
        return "/product/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute ProductVO vo) {
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(vo.getProductName());
        product.setProductPrice(vo.getProductPrice());
        product.setProductStock(vo.getProductStock());
        productService.updateById(product);
        return "redirect:/product";
    }
}
