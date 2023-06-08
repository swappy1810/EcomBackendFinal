package com.example.roleBased.controller;

import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    //add the order using user id and product id
    @PostMapping("/users/{userId}/product/{productId}/orders")
    public ResponseEntity<OrderDto> createCart(@RequestBody OrderDto cartDto, @PathVariable Integer productId, @PathVariable Integer userId){
        OrderDto createCart = this.orderService.createOrder(cartDto,productId,userId);
        return new ResponseEntity<OrderDto>(createCart, HttpStatus.CREATED);
    }

    //get orders by user id
    @GetMapping("users/{userId}/order")
    public ResponseEntity<List<OrderDto>> getByUser(@PathVariable Integer userId){
        List<OrderDto> cartDtos = this.orderService.getOrderByUser(userId);
        return new ResponseEntity<List<OrderDto>>(cartDtos,HttpStatus.OK);
    }

    //get order by product id
    @GetMapping("product/{productId}/order")
    public ResponseEntity<List<OrderDto>> getByCategory(@PathVariable Integer productId){
        List<OrderDto> cartDtos = this.orderService.getOrderByProduct(productId);
        return new ResponseEntity<List<OrderDto>>(cartDtos,HttpStatus.OK);
    }

}
