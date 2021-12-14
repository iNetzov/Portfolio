package com.example.demo.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StoresDeleteBindingModel {
    private String storeName;

    public StoresDeleteBindingModel() {
    }

    @NotNull
    @Size(min = 1)
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
