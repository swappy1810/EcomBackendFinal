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
    public OrderDto createOrder(OrderDto cartDto, Integer productId, Integer userId) {
        User user =  this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id" +userId));

        Product product =  this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("product not found with this id" +productId));

        Order cart = this.modelMapper.map(cartDto, Order.class);
        cart.setAddedDate(new Date());
        cart.setOrderStatus(cartDto.getStatus());
        cart.setQuantity(cartDto.getQuantity());
        cart.setAddedDate(cartDto.getAddedDate());
        cart.setProduct(product);
        cart.setUser(user);

        Order newCart = this.orderDao.save(cart);
        return this.modelMapper.map(newCart, OrderDto.class);
    }

    //update the order by order id

    public Order updateOrder(OrderDto cartDto, Integer orderId) {
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

    public OrderDto getOrderById(Integer cartId) {
        return null;
    }

//get order by product by product id

    public List<OrderDto> getOrderByProduct(Integer productId) {
        Product product= this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product with cart not found with this id "+productId));
        List<Order> carts = this.orderDao.findByProduct(product);
        List<OrderDto> cartDtos = carts.stream().map((x) -> this.modelMapper.map(x, OrderDto.class)).collect(Collectors.toList());
        return cartDtos;
    }

//get order by user by user id

    public List<OrderDto> getOrderByUser(Integer userId) {
        User user = this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("post category not found with this id "+userId));
        List<Order> carts = this.orderDao.findByUser(user);
        List<OrderDto> cartDtos = carts.stream().map((x) -> this.modelMapper.map(x, OrderDto.class)).collect(Collectors.toList());
        return cartDtos;
    }
}
