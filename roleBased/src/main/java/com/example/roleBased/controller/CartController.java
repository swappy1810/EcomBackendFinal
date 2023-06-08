package com.example.roleBased.controller;

import com.example.roleBased.entity.Cart;
import com.example.roleBased.serviceImpl.CartServiceImpl;
import com.example.roleBased.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private UserServiceImpl userService;

    //get added cart item
//    @PostMapping("/addtocart/{productId}")
//    public String addToCart(@PathVariable(name = "productId") Integer productId){
//        return cartService.addToCart(productId);
//    }
//
//    @GetMapping("/getCartDetails")
//    public List<Cart> getDetails(){
//        return cartService.getCartDetails();
//    }
}
