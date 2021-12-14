package com.example.demo.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderAddBindingModel {

    private String product;
    private String store;


    public OrderAddBindingModel() {
    }

    @NotNull
    @Size(min = 1)
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    @NotNull
    @Size(min = 1)
    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

}
