package com.example.demo.models.entity;

import com.example.demo.models.entity.enums.CategoryEntityNameEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    private CategoryEntityNameEnum name;

    public CategoryEntity() {
    }

    @Enumerated(EnumType.STRING)
    @NotNull
    public CategoryEntityNameEnum getName() {
        return name;
    }

    public void setName(CategoryEntityNameEnum category) {
        this.name = category;
    }
}
