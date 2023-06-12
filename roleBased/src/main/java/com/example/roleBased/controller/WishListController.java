package com.example.roleBased.controller;

import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.dto.WishListDto;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.WishList;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import com.example.roleBased.serviceImpl.WishListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("wishlist")
public class WishListController {
    @Autowired
    private WishListServiceImpl wishListService;

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("save/{userId}/product/{productId}")
    public ResponseEntity<WishListDto> createWishList(@RequestBody WishListDto wishListDto, @PathVariable Integer userId, @PathVariable Integer productId){
        WishListDto createWishListDto = this.wishListService.createWishList(wishListDto,userId,productId);
        return new ResponseEntity<WishListDto>(createWishListDto, HttpStatus.CREATED);
    }

    //ReadWishlist
    @GetMapping("getList/{userId}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable Integer userId) {

        //get wishlist
        List<WishList> body = wishListService.readWishList(userId);

        //create productDTO from productId in wishlist
        List<ProductDto> products = new ArrayList<ProductDto>();
        for (WishList wishList : body) {
            products.add(productService.productToDto(wishList.getProduct()));
        }

        return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
    }




}
