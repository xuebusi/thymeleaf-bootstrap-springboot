package com.example.thymeleaf.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductVO {
    @NotEmpty(message = "产品图片不能为空")
    private String productImg;

    @NotEmpty(message = "产品名称不能为空")
    private String productName;

    @NotNull(message = "产品价格不能为空")
    @Min(value = 1, message = "产品价格不能低于1")
    @Max(value = 9999, message = "产品价格不能高于9999")
    private BigDecimal productPrice;

    @NotNull(message = "产品数量不能为空")
    @Min(value = 1, message = "产品数量不能低于1")
    @Max(value = 9999, message = "产品数量不能高于9999")
    private Integer productStock;

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }
}
