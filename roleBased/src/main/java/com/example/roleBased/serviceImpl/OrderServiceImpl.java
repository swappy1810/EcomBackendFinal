package com.example.roleBased.serviceImpl;

import com.example.roleBased.config.JwtRequestFilter;
import com.example.roleBased.dao.*;
import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.entity.*;
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
    private CartDetailDao cartDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDao cartDao;

    ModelMapper modelMapper = new ModelMapper();

//add order to orders
    public OrderDto createOrder(OrderDto orderDto, Integer productId) {
         Product product =  this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("product not found with this id" +productId));

        Order order = this.modelMapper.map(orderDto, Order.class);
        order.setAddedDate(new Date());
        order.setStatus(orderDto.getStatus());
        order.setQuantity(orderDto.getQuantity());
        order.setAddedDate(orderDto.getAddedDate());
        order.setAddressLine1(orderDto.getAddressLine1());
        order.setAddressLine2(orderDto.getAddressLine2());
        order.setCity(orderDto.getCity());
        order.setCountry(order.getCountry());
        order.setState(order.getState());
        order.setMobileNo(order.getMobileNo());
        order.setQuantity(orderDto.getQuantity());
        order.setZipCode(orderDto.getZipCode());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setProduct(product);
        order.setUserCartId(orderDto.getUserCartId());
        Order newCart = this.orderDao.save(order);

        System.out.println("entered clear cart");
        Cart cart = cartDao.findById(order.getUserCartId()).get();
        System.out.println(cart.getUserCartId());
        System.out.println(cart);
        cart.getCartDetails().clear();
        System.out.println(cart);
        cart.setTotalPrice(0);
        cartDao.save(cart);

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
