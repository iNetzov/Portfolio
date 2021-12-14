package com.example.demo.service;

import com.example.demo.models.entity.CategoryEntity;
import com.example.demo.models.entity.enums.CategoryEntityNameEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity  findByCategoryNameEnum(CategoryEntityNameEnum nameEnum);
}
