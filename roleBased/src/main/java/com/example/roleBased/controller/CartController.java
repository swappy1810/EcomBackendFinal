package com.example.roleBased.controller;

import com.example.roleBased.config.JwtUtil;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dto.CartDetailDto;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.CartDetails;
import com.example.roleBased.entity.User;
import com.example.roleBased.entity.Wishlist;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.serviceImpl.CartServiceImpl;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private UserDao userDao;

   @Autowired
   private CartServiceImpl cartServiceImpl;

   @Autowired
   private ProductServiceImpl productService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/addtocart/{productId}/{userId}")
    public String addToCart(HttpServletRequest request,@RequestBody CartDetails cartDetails, @PathVariable(name = "productId") Integer productId, @PathVariable(name = "userId") Integer userId){
       User user = cartServiceImpl.getUser(request);
        return cartServiceImpl.addToCart(cartDetails,productId,userId);
    }

    @PreAuthorize("hasRole('User')")
    @DeleteMapping("/deleteCart/{productId}")
    public ResponseEntity<ApiResponse> deleteCart(HttpServletRequest request,@PathVariable Integer productId){
        User user = cartServiceImpl.getUser(request);
        this.cartServiceImpl.deleteCartById(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("product is successfully deleted from cart",true), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/{userId}")
    public ResponseEntity<List<ProductDto>> getAllCart(HttpServletRequest request,@PathVariable Integer userId){
        User user = cartServiceImpl.getUser(request);
        //get wishlist
        List<CartDetails> body = cartServiceImpl.getCartDetails(userId);

        //create productDTO from productId in wishlist
        List<ProductDto> products = new ArrayList<ProductDto>();
        for (CartDetails cartDetails : body) {
            products.add(productService.productToDto(cartDetails.getProduct()));
        }
        return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
    }

}
