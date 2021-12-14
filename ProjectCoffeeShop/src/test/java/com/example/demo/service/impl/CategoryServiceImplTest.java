package com.example.demo.service.impl;


import com.example.demo.models.entity.CategoryEntity;
import com.example.demo.models.entity.enums.CategoryEntityNameEnum;
import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    private CategoryServiceImpl serviceToTest;
    private CategoryEntity category;
    private CategoryEntityNameEnum categoryEnum;

    @Mock
    private CategoryRepository mockUserRepository;

    @BeforeEach
    void inIt(){
        serviceToTest = new CategoryServiceImpl(mockUserRepository);
        category = new CategoryEntity();
        categoryEnum = CategoryEntityNameEnum.DRINK;
        category.setName(categoryEnum);
        mockUserRepository.save(category);
    }



    @Test
    void CategoryFindByNameNOTFOUND(){
        CategoryEntity expected = new CategoryEntity();
        expected.setName(CategoryEntityNameEnum.DRINK);
        mockUserRepository.save(expected);
        CategoryEntity actual = serviceToTest.findByCategoryNameEnum(CategoryEntityNameEnum.DRINK);
        Assertions.assertNull(actual);
    }




}
