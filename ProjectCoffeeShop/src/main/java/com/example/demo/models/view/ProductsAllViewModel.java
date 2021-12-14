package com.example.demo.models.view;

import com.example.demo.models.entity.enums.CategoryEntityNameEnum;

import java.math.BigDecimal;

public class ProductsAllViewModel {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String pictureUrl;
    private CategoryEntityNameEnum category;

    public ProductsAllViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public CategoryEntityNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEntityNameEnum category) {
        this.category = category;
    }
}
