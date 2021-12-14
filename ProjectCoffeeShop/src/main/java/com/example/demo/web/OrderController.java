package com.example.demo.web;

import com.example.demo.models.binding.OrderAddBindingModel;
import com.example.demo.models.entity.OrderEntity;
import com.example.demo.models.service.OrderServiceModel;
import com.example.demo.models.view.ProductsAllViewModel;
import com.example.demo.models.view.StoresAllViewModel;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.StoreService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("orders")
public class OrderController {
    private final ProductService productService;
    private final StoreService storeService;
    private final OrderService orderService;

    public OrderController(ProductService productService, StoreService storeService,OrderService orderService) {
        this.productService = productService;
        this.storeService = storeService;
        this.orderService = orderService;
    }


    @GetMapping("/add")
    public String createOrder(Model model){
        List<ProductsAllViewModel> allProducts = productService.getAllProducts();
        List<StoresAllViewModel> allStores = storeService.getAllStores();
        model.addAttribute("productsForDropDown",allProducts);
        model.addAttribute("storesForDropDown",allStores);
        return "order-add";
    }
    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel(){
        return new OrderAddBindingModel();
    }

    @PostMapping("/add")
    public String createOrderConfirm(@AuthenticationPrincipal UserDetails userDetails,@Valid OrderAddBindingModel orderAddBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes){
       if (bindingResult.hasErrors()){
           redirectAttributes
                   .addFlashAttribute("orderAddBindingModel",orderAddBindingModel)
                   .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel",bindingResult);
           return "redirect:add";
       }
        String store = orderAddBindingModel.getStore();
        String product = orderAddBindingModel.getProduct();
        String username = userDetails.getUsername();
        OrderServiceModel order = orderService.createOrderServiceModel(username,product,store);
        orderService.addOrder(order);
        return "afterOrder";
    }



}
