package com.example.demo.service.impl;

import com.example.demo.models.entity.ProductEntity;
import com.example.demo.models.entity.enums.CategoryEntityNameEnum;
import com.example.demo.models.service.ProductServiceModel;
import com.example.demo.models.view.ProductsAllViewModel;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public ProductEntity findByProductName(String name) {
        ProductEntity product = productRepository.findByName(name).orElse(null);
        return product;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        ProductEntity product = modelMapper.map(productServiceModel, ProductEntity.class);
        product.setCategory(categoryService.findByCategoryNameEnum(productServiceModel.getCategory()));
        productRepository.save(product);
    }

    @Override
    public void initProducts() {
        if (productRepository.count() != 0) {
            return;
        }
        ProductEntity product = new ProductEntity();
        product.setName("Capucino");
        product.setDescription("this is a  really good and hot coffee");
        product.setPictureUrl("http://www.green-coffee-shop.com/blog/wp-content/uploads/2021/11/171026-better-coffee-boost-se-329p.jpg");
        BigDecimal priceProduct1 = new BigDecimal("15.55");
        product.setPrice(priceProduct1);
        product.setCategory(categoryService.findByCategoryNameEnum(CategoryEntityNameEnum.DRINK));

        ProductEntity product2 = new ProductEntity();
        product2.setName("Croassan");
        product2.setDescription("this is a  really good Croassan");
        product2.setPictureUrl("https://keyassets-p2.timeincuk.net/wp/prod/wp-content/uploads/sites/63/2013/01/Croissant.jpg");
        BigDecimal priceProduct2 = new BigDecimal("10.55");
        product2.setPrice(priceProduct2);
        product2.setCategory(categoryService.findByCategoryNameEnum(CategoryEntityNameEnum.FOOD));

        ProductEntity product3 = new ProductEntity();
        product3.setName("Water-Devin");
        product3.setDescription("this is a  really bitter water");
        product3.setPictureUrl("https://www.zehnders.com/zchefs/wp-content/uploads/2020/05/aquafina-bottle.jpg");
        BigDecimal priceProduct3 = new BigDecimal("2.55");
        product3.setPrice(priceProduct3);
        product3.setCategory(categoryService.findByCategoryNameEnum(CategoryEntityNameEnum.DRINK));

        ProductEntity product4 = new ProductEntity();
        product4.setName("Starbugs coffee beans");
        product4.setDescription("this is a  really bitter water");
        product4.setPictureUrl("https://m.media-amazon.com/images/I/81j4EotdQBL._SL1500_.jpg");
        BigDecimal priceProduct4 = new BigDecimal("25.55");
        product4.setPrice(priceProduct4);
        product4.setCategory(categoryService.findByCategoryNameEnum(CategoryEntityNameEnum.BEANS));

        List<ProductEntity> productsToAddList = new ArrayList<>();
        productsToAddList.add(product);
        productsToAddList.add(product2);
        productsToAddList.add(product3);
        productsToAddList.add(product4);

        productRepository.saveAll(productsToAddList);
    }

    @Override
    public List<ProductsAllViewModel> getAllProducts() {
        return productRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }



    private ProductsAllViewModel map(ProductEntity offerEntity) {
        ProductsAllViewModel view = this.modelMapper
                .map(offerEntity, ProductsAllViewModel.class);
        view.setCategory(offerEntity.getCategory().getName());

        return view;
    }
}
