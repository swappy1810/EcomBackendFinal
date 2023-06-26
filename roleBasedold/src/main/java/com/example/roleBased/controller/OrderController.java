package com.example.roleBased.controller;

import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.Order;
import com.example.roleBased.entity.Wishlist;
import com.example.roleBased.serviceImpl.OrderServiceImpl;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ProductServiceImpl productService;

    //add the order using user id and product id
    @PreAuthorize("hasRole('User')")
    @PostMapping("product/{productId}/orders/{isSingleCheckout}/{userId}")
    public ResponseEntity<OrderDto> createCart(@RequestBody OrderDto cartDto, @PathVariable Integer productId,Boolean isSingleCheckout,@PathVariable Integer userId){
        OrderDto createCart = this.orderService.createOrder(cartDto,productId,isSingleCheckout,userId);
        return new ResponseEntity<OrderDto>(createCart, HttpStatus.CREATED);
    }

    //get orders by user id
    @PreAuthorize("hasRole('User')")
    @GetMapping("users/{userId}/order")
    public ResponseEntity<List<ProductDto>> getByUserId(@PathVariable Integer userId){
        //get wishlist
        List<Order> body = orderService.getOrderByUser(userId);

        //create productDTO from productId in wishlist
        List<ProductDto> products = new ArrayList<ProductDto>();
        for (Order order : body) {
            products.add(productService.productToDto(order.getProduct()));
        }

        return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
    }

    //get order by product id
    @PreAuthorize("hasRole('User')")
    @GetMapping("product/{productId}/order")
    public ResponseEntity<List<OrderDto>> getByCategory(@PathVariable Integer productId){
        List<OrderDto> cartDtos = this.orderService.getOrderByProduct(productId);
        return new ResponseEntity<List<OrderDto>>(cartDtos,HttpStatus.OK);
    }

}
