package com.example.demo.models.service;

import com.example.demo.models.entity.ProductEntity;
import com.example.demo.models.entity.StoreEntity;
import com.example.demo.models.entity.UserEntity;

import java.util.List;

public class OrderServiceModel {
    private Long id;
    private UserEntity customer;
    private ProductEntity product;
    private StoreEntity store;

    public OrderServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }
}
