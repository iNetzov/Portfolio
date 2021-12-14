package com.example.demo.service;

import com.example.demo.models.entity.StoreEntity;
import com.example.demo.models.service.StoreServiceModel;
import com.example.demo.models.view.StoresAllViewModel;

import java.util.List;

public interface StoreService {
    StoreEntity findByName(String name);

    void addStore(StoreServiceModel storeServiceModel);
    void initStores();

    List<StoresAllViewModel> getAllStores();

    void deleteStore(Long id);
}
