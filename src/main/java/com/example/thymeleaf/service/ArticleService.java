package com.example.thymeleaf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.thymeleaf.mapper.ArticleMapper;
import com.example.thymeleaf.model.Article;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleService extends ServiceImpl<ArticleMapper, Article> {
    @Resource
    private ArticleMapper articleMapper;

    public Page<Article> getArticles(int page, int size, String articleName) {
        Page<Article> pageRequest = new Page<>(page, size);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(articleName)) {
            queryWrapper.lambda().like(Article::getArticleName, articleName);
        }

        return articleMapper.selectPage(pageRequest, queryWrapper);
    }
}
