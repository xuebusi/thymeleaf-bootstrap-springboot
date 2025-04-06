package com.example.thymeleaf.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.thymeleaf.model.Product;
import com.example.thymeleaf.model.ProductVO;
import com.example.thymeleaf.service.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Slf4j
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
        model.addAttribute("productImg", product.getProductImg());

        ProductVO vo = new ProductVO();
        vo.setProductName(product.getProductName());
        vo.setProductPrice(product.getProductPrice());
        vo.setProductStock(product.getProductStock());

        model.addAttribute("vo", vo);
        return "/product/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute ProductVO vo) {
        Product product = productService.getById(id);
        if (StringUtils.isNotBlank(vo.getProductName())) {
            product.setProductName(vo.getProductName());
        }
        if (vo.getProductPrice() != null) {
            product.setProductPrice(vo.getProductPrice());
        }
        if (vo.getProductStock() != null) {
            product.setProductStock(vo.getProductStock());
        }
        if (!vo.getImageFile().isEmpty()) {
            String uploadDir = "public/images/";
            Path oldPath = Paths.get(uploadDir + product.getProductImg());
            try {
                Files.delete(oldPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            MultipartFile image = vo.getImageFile();
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

            try (InputStream in = image.getInputStream()) {
                Files.copy(in, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log.error(e.getLocalizedMessage());
            }

            product.setProductImg(storageFileName);
        }

        productService.updateById(product);
        return "redirect:/product";
    }
}
