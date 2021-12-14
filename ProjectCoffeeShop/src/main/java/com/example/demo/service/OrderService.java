package com.example.demo.service;

import com.example.demo.models.service.OrderServiceModel;

public interface OrderService {
    OrderServiceModel createOrderServiceModel(String username, String product, String store);

    void addOrder(OrderServiceModel order);
    void moveToLogs();
}
