package com.example.demo.service.impl;

import com.example.demo.models.entity.StoreEntity;
import com.example.demo.models.view.StoresAllViewModel;
import com.example.demo.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {

    private StoreEntity store;
    private  StoreServiceImpl testService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Mock
    private StoreRepository mockStoreRepository;

    @BeforeEach
    void inIt(){
        store = new StoreEntity();
        store.setName("Name  1");
        store.setAddress("address 1");
        store.setUrl(" here  is URL :demo 1");
        testService = new StoreServiceImpl(mockStoreRepository,modelMapper);
        mockStoreRepository.save(store);
    }

    @Test
    void addStore(){
        Mockito.when(mockStoreRepository.findAll()).thenReturn((List.of(store)));
        StoreEntity store2 = new StoreEntity();
        store2.setName("Name  12");
        store2.setAddress("address 12");
        store2.setUrl(" here  is 2URL :demo 1");
        mockStoreRepository.save(store2);

        Assertions.assertEquals(1,mockStoreRepository.findAll().size());
    }

    @Test
    void storeInIt(){
        Mockito.when(mockStoreRepository.findAll()).thenReturn((List.of(store)));
        testService.initStores();
        Assertions.assertEquals(1,mockStoreRepository.findAll().size());
    }

    @Test
     void map(){
        List<StoresAllViewModel> allStores = testService.getAllStores();
        Assertions.assertEquals(allStores.size(),mockStoreRepository.findAll().size());
    }

    @Test
    void findByName(){

        mockStoreRepository.findByName("Name  1");

        System.out.println(mockStoreRepository.findByName("Name  1"));

//        Assertions.assertEquals(mockStoreRepository.findByName("Name  1"),store);
    }

}
