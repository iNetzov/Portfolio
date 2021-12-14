package com.example.demo.models.binding;

import com.example.demo.models.entity.enums.CategoryEntityNameEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductAddBindingModel {
    private String name;
    private BigDecimal price;
    private String description;
    private String pictureUrl;
    private CategoryEntityNameEnum category;

    public ProductAddBindingModel() {
    }

    @NotNull
    @Size(min = 5,max = 35)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Positive
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Size(min = 15)
    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Size(min = 5)
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @NotNull
    public CategoryEntityNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEntityNameEnum category) {
        this.category = category;
    }
}
