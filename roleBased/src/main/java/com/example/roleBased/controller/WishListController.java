package com.example.roleBased.controller;

import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.dto.WishlistDto;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Wishlist;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import com.example.roleBased.serviceImpl.WishListServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    ModelMapper modelMapper = new ModelMapper();

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
            products.add(productToDto(wishList.getProduct()));
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ApiResponse deleteByUserId(@PathVariable Integer productId)
    { wishListService.deleteWishList(productId);
        return new ApiResponse("Product removed from wishlist",true); }


    public ProductDto productToDto(Product product) {
        return modelMapper.map(product,ProductDto.class);
    }
}
