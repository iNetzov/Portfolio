package com.example.demo.service;

import com.example.demo.models.entity.ProductEntity;
import com.example.demo.models.service.ProductServiceModel;
import com.example.demo.models.view.ProductsAllViewModel;

import java.util.List;

public interface ProductService {
    ProductEntity findByProductName(String name);

    void addProduct(ProductServiceModel productServiceModel);
    void initProducts();
    List<ProductsAllViewModel> getAllProducts();

    void deleteProduct(Long id);
}
