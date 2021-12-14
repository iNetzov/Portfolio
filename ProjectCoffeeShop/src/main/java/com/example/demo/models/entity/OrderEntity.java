package com.example.demo.models.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    private UserEntity customer;
    private ProductEntity product;
    private StoreEntity store;

    public OrderEntity() {
    }

    @ManyToOne
    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    @ManyToOne()
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity products) {
        this.product = products;
    }

    @ManyToOne
    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }
}
