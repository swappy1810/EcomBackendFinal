package com.example.roleBased.controller;

import com.example.roleBased.dto.CartDetailDto;
import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.entity.Cart;
import com.example.roleBased.entity.CartDetails;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.serviceImpl.CartServiceImpl;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CartController {

   @Autowired
   private CartServiceImpl cartServiceImpl;

   @Autowired
   private ProductServiceImpl productService;

   public ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/addtocart/{productId}/{userId}")
    public String addToCart(@RequestBody CartDetails cartDetails, @PathVariable(name = "productId") Integer productId, @PathVariable(name = "userId") Integer userId){
        return cartServiceImpl.addToCart(cartDetails,productId,userId);
    }


    @DeleteMapping("/deleteCart/{productId}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Integer productId){
        this.cartServiceImpl.deleteCartById(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("product is successfully deleted from cart",true), HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDetails>> getAllCart(@PathVariable Integer userId){
         //get wishlist
        List<CartDetails> body = cartServiceImpl.getCartDetails(userId);

        //create productDTO from productId in wishlist
        List<CartDetails> cartDetails1 = new ArrayList<>();
        for (CartDetails cartDetails : body) {
            cartDetails1.add(cartDetails);
        }
        return new ResponseEntity<>(cartDetails1, HttpStatus.OK);
    }

    //update cart by cart id

    @PutMapping("/{userId}/{productId}")
    public ResponseEntity<CartDetails> updateCart(@Valid @RequestBody CartDetails cartDetails,@PathVariable Integer userId,@PathVariable Integer productId){
        CartDetails updatedCart = this.cartServiceImpl.updateCart(cartDetails,userId,productId);
        return ResponseEntity.ok(updatedCart);
    }

}
