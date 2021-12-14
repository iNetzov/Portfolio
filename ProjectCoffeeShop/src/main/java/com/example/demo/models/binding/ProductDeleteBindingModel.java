package com.example.demo.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDeleteBindingModel {
   private String productName;

    public ProductDeleteBindingModel() {
    }

    @NotNull
    @Size(min = 1)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
