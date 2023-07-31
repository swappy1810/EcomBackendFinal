package com.example.roleBased.controller;

import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.dto.OrderItemDto;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.Order;
import com.example.roleBased.entity.Wishlist;
import com.example.roleBased.serviceImpl.OrderServiceImpl;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
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
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ProductServiceImpl productService;

    ModelMapper modelMapper = new ModelMapper();

    //add the order using user id and product id
    @PostMapping("product/{productId}/orders/{isSingleCheckout}/{userId}/{quantity}")
    public String createCart(@RequestBody Order order, @PathVariable Integer productId,Boolean isSingleCheckout,@PathVariable Integer userId,@PathVariable Integer quantity){
        String createCart = this.orderService.createOrder(order,productId,isSingleCheckout,userId,quantity);
        return "Order Placed!";
    }

    //get orders by user id
    @GetMapping("users/{userId}/order")
    public ResponseEntity<List<Order>> getByUserId(@PathVariable Integer userId){
        //get wishlist
        List<Order> body = orderService.getOrderByUser(userId);

        //create productDTO from productId in wishlist
        List<Order> orderItemDtos = new ArrayList<Order>();
        for (Order order : body) {
            orderItemDtos.add(order);
        }
        return new ResponseEntity<List<Order>>(orderItemDtos, HttpStatus.OK);
    }

    //get order by product id
    @GetMapping("product/{productId}/order")
    public ResponseEntity<List<Order>> getByCategory(@PathVariable Integer productId){
        List<Order> cartDtos = this.orderService.getOrderByProduct(productId);
        return new ResponseEntity<List<Order>>(cartDtos,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrder(){
        return ResponseEntity.ok(this.orderService.getAllOrder());
    }

//    //product to dto fetch
//    public OrderDto orderToDto(Order order) {
//        OrderDto orderDto = modelMapper.map(order,OrderDto.class);
//        return  orderDto;
//    }
//
//    public OrderItemDto orderNewDto(Order newOrder) {
//        OrderItemDto order =  modelMapper.map(newOrder, OrderItemDto.class);
//        return order;
//    }
}