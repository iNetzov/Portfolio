package com.example.demo.service.impl;

import com.example.demo.models.entity.*;
import com.example.demo.models.service.OrderServiceModel;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final ProductService productService;
    private final StoreService storeService;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final LogService logService;

    public OrderServiceImpl(UserService userService, ProductService productService, StoreService storeService, ModelMapper modelMapper, OrderRepository orderRepository, LogService logService) {
        this.userService = userService;
        this.productService = productService;
        this.storeService = storeService;
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.logService = logService;
    }


    @Override
    public OrderServiceModel createOrderServiceModel(String username, String productName, String storeName) {
        OrderServiceModel orderServiceModel = new OrderServiceModel();
        UserEntity creator = userService.findByUsername(username);
        ProductEntity product = productService.findByProductName(productName);
        StoreEntity store = storeService.findByName(storeName);
        orderServiceModel.setCustomer(creator);
        orderServiceModel.setProduct(product);
        orderServiceModel.setStore(store);
        return orderServiceModel;
    }

    @Override
    public void addOrder(OrderServiceModel order) {
        OrderEntity orderEntity = modelMapper.map(order,OrderEntity.class);
        orderRepository.save(orderEntity);
    }

    @Override
    public void moveToLogs() {
        if (orderRepository.count() == 0){
            return;
        }
        List<OrderEntity> allOrder = orderRepository.findAll();
        for (OrderEntity o : allOrder) {
            LogEntity logEntity = new LogEntity();
            logEntity.setOrderId(o.getId());
            logEntity.setClientName(o.getCustomer().getFullName());
            logEntity.setDone(LocalDateTime.now());
            logEntity.setProductName(o.getProduct().getName());
            logEntity.setStoreName(o.getStore().getName());

            logService.addToLog(logEntity);

        }
        orderRepository.deleteAll();
    }

}
