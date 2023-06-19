package com.example.roleBased.controller;

import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.dto.WishlistDto;
import com.example.roleBased.entity.Wishlist;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import com.example.roleBased.serviceImpl.WishListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class WishListController {

    @Autowired
    private WishListServiceImpl wishListService;

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("save/{userId}/product/{productId}")
    public ResponseEntity<WishlistDto> createWishList(@RequestBody WishlistDto wishListDto, @PathVariable Integer userId, @PathVariable Integer productId){
        WishlistDto createWishListDto = this.wishListService.createWishList(wishListDto,userId,productId);
        return new ResponseEntity<WishlistDto>(createWishListDto, HttpStatus.CREATED);
    }

    //ReadWishlist
    @GetMapping("getList/{userId}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable Integer userId) {

        //get wishlist
        List<Wishlist> body = wishListService.readWishList(userId);

        //create productDTO from productId in wishlist
        List<ProductDto> products = new ArrayList<ProductDto>();
        for (Wishlist wishList : body) {
            products.add(productService.productToDto(wishList.getProduct()));
        }

        return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
    }

}