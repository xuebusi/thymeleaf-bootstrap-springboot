package com.example.thymeleaf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.thymeleaf.mapper.ProductMapper;
import com.example.thymeleaf.model.Product;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Resource
    private ProductMapper productMapper;

    public Page<Product> getProducts(int page, int size, String productName) {
        Page<Product> pageRequest = new Page<>(page, size);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(productName)) {
            queryWrapper.lambda().like(Product::getProductName, productName);
        }

        return productMapper.selectPage(pageRequest, queryWrapper);
    }
}
