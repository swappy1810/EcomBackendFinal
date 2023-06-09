package com.example.roleBased.controller;

import com.example.roleBased.dto.CartDto;
import com.example.roleBased.dto.CartNewDto;
import com.example.roleBased.entity.Cart;
import com.example.roleBased.entity.User;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.serviceImpl.CartServiceImpl;
import com.example.roleBased.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carts")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private UserServiceImpl userService;

    //get added cart item
    @PostMapping("/addtocart/{productId}")
    public CartNewDto addToCart(@RequestBody CartNewDto cartNewDto, @PathVariable(name = "productId") Integer productId) {
        return cartService.addToCart(cartNewDto, productId);
    }



   /* @GetMapping("/getCartDetails")
    public List<Cart> getDetails(){
        return cartService.getCartDetails();
    }*/

    // @GetMapping("/cartDetails/{user_username}")
    //public List<Cart> CartDetails(@PathVariable User user_username){
    //   return cartService.CartDetails(user_username);

    //}
    //delete cart with particular cartId
    @DeleteMapping("/deleteCart/{cartNewId}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Integer cartNewId) {
        this.cartService.deleteCart(cartNewId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Cart is successfully deleted with", true), HttpStatus.OK);
    }
}