package com.example.demo.service.impl;

import com.example.demo.models.entity.ProductEntity;
import com.example.demo.models.entity.StoreEntity;
import com.example.demo.models.service.StoreServiceModel;
import com.example.demo.models.view.ProductsAllViewModel;
import com.example.demo.models.view.StoresAllViewModel;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    public StoreServiceImpl(StoreRepository storeRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public StoreEntity findByName(String name) {
        return storeRepository.findByName(name).orElse(null);
    }


    @Override
    public void addStore(StoreServiceModel storeServiceModel) {
    StoreEntity store = modelMapper.map(storeServiceModel,StoreEntity.class);

    storeRepository.save(store);
    }

    @Override
    public void initStores() {
        if (storeRepository.count() != 0){
            return;
        }

        StoreEntity store1 = new StoreEntity();
        store1.setAddress("Mihail Petkov 13");
        store1.setName("Barista store:Mihail Petkov");
        store1.setUrl("https://eposnow-marketing.s3.eu-west-1.amazonaws.com/public/Uploads/nafinia-putra-Kwdp-0pok-I-unsplash-v2.jpg");
        StoreEntity store2 = new StoreEntity();
        store2.setAddress("Akademik Arnuldov 33");
        store2.setName("Barista store:33");
        store2.setUrl("https://archello.s3.eu-central-1.amazonaws.com/images/2020/12/04/estudio-ch--varro-mustapan-coffee-shop-shops-archello.1607075335.1427.jpg");

        StoreEntity store3 = new StoreEntity();
        store3.setAddress("Suhata reka bl 217");
        store3.setName("reka Barista");
       store3.setUrl("https://img1.10bestmedia.com/Images/Photos/371753/Passion-House-Coffee-credit-Belen-Aquino_54_990x660.jpg");
        List<StoreEntity> storesToAdd = new LinkedList<>();
        storesToAdd.add(store1);
        storesToAdd.add(store2);
        storesToAdd.add(store3);

        storeRepository.saveAll(storesToAdd);


    }

    @Override
    public List<StoresAllViewModel> getAllStores() {
       return storeRepository
               .findAll()
               .stream()
               .map(this::map)
               .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    private StoresAllViewModel map(StoreEntity storeEntity) {
        StoresAllViewModel view = this.modelMapper
                .map(storeEntity, StoresAllViewModel.class);
        return view;
    }
}
