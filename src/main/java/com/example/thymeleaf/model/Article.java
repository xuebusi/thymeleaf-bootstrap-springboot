package com.example.thymeleaf.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@TableName("tb_article")
public class Article {
    @TableId(type = IdType.AUTO)
    private Long articleId;

    @NotEmpty(message = "文章名称不能为空")
    private String articleName;

    @NotEmpty(message = "文章内容不能为空")
    private String articleContent;

    @NotEmpty(message = "文章作者不能为空")
    private String articleAuthor;

    private Integer previewCount;
    private Date createTime;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Integer getPreviewCount() {
        return previewCount;
    }

    public void setPreviewCount(Integer previewCount) {
        this.previewCount = previewCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}