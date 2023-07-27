package com.example.roleBased.controller;

import com.example.roleBased.entity.Order;
import com.example.roleBased.entity.OrderItems;
import com.example.roleBased.serviceImpl.OrderServiceImpl;
import com.example.roleBased.serviceImpl.ProductServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    @PostMapping("order/{userId}/{quantity}/{productId}")
    public String createCart(@RequestBody OrderItems cartDto, @PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer quantity){
        System.out.println("in order controller");
        System.out.println(cartDto);
        return this.orderService.placeOrder(cartDto, userId,quantity,productId);
    }

    //add the order using user id and product id
    @PostMapping("order/{userId}")
    public String createCart(@RequestBody OrderItems cartDto, @PathVariable Integer userId){
        return this.orderService.createOrder(cartDto, userId);
    }

    //get orders by user id
    @GetMapping("users/{userId}/order")
    public ResponseEntity<List<Order>> getByUserId(@PathVariable Integer userId){
        //get wishlist
        List<Order> body = orderService.getOrderByUser(userId);

        //create productDTO from productId in wishlist
        List<Order> orderItemDtos = new ArrayList<>();
        for (Order order : body) {
            orderItemDtos.add(order);
        }
        return new ResponseEntity<List<Order>>(orderItemDtos, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrder(){
        return ResponseEntity.ok(this.orderService.getAllOrder());
    }

}
