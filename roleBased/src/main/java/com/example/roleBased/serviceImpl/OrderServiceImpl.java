package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.OrderDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.entity.Order;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;


    ModelMapper modelMapper = new ModelMapper();

//add order to orders
    public OrderDto createOrder(OrderDto orderDto, Integer productId) {
         Product product =  this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("product not found with this id" +productId));

        Order order = this.modelMapper.map(orderDto, Order.class);
        order.setAddedDate(new Date());
        order.setStatus(orderDto.getStatus());
        order.setQuantity(orderDto.getQuantity());
        order.setAddedDate(orderDto.getAddedDate());
        order.setProduct(product);
        order.setUserCartId(orderDto.getUserCartId());
        Order newCart = this.orderDao.save(order);
        return this.modelMapper.map(newCart, OrderDto.class);
    }

    //update the order by order id

    public Order updateOrder(OrderDto orderDto, Integer orderId) {
        return null;
    }

    //delete order by order id

    public void deleteOrder(Integer orderId) {

    }

    //get all order

    public List<OrderDto> getAllOrder() {
        return null;
    }

    //get order by order id

    public OrderDto getOrderById(Integer orderId) {
        return null;
    }

//get order by product by product id

    public List<OrderDto> getOrderByProduct(Integer productId) {
        Product product= this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product with cart not found with this id "+productId));
        List<Order> orders = this.orderDao.findByProduct(product);
        List<OrderDto> orderDtos = orders.stream().map((x) -> this.modelMapper.map(x, OrderDto.class)).collect(Collectors.toList());
        return orderDtos;
    }

//get order by user by user id

    public List<OrderDto> getOrderByUser(Integer userCartId) {
        User user = this.userDao.findById(userCartId).orElseThrow(()->new ResourceNotFoundException(" userCart Id not found with this id "+userCartId));
        List<Order> carts = this.orderDao.findByUserCartId(userCartId);
        List<OrderDto> cartDtos = carts.stream().map((x) -> this.modelMapper.map(x, OrderDto.class)).collect(Collectors.toList());
        return cartDtos;
    }
}
