package com.example.demo.service.impl;

import com.example.demo.models.entity.CategoryEntity;
import com.example.demo.models.entity.enums.CategoryEntityNameEnum;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void initCategories() {
        if (categoryRepository.count() != 0){
            return;
        }
        Arrays.stream(CategoryEntityNameEnum.values()).forEach(categoryNameEnum->{
            CategoryEntity category = new CategoryEntity();
            category.setName(categoryNameEnum);

            categoryRepository.save(category);
        });

    }

    @Override
    public CategoryEntity findByCategoryNameEnum(CategoryEntityNameEnum nameEnum) {
        return categoryRepository.findByName(nameEnum).orElse(null);
    }
}
