package com.example.roleBased.serviceImpl;

import com.example.roleBased.config.JwtRequestFilter;
import com.example.roleBased.dao.*;
import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.dto.OrderItemDto;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public String createOrder(OrderDto orderDto, Integer productId,Boolean isSingleCheckout,Integer userId) {
//        if(JwtRequestFilter.CURRENT_USER!=null) {
            User user = this.userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with user Id" + userId));
            Product product = this.productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with this id" + productId));
            if (Boolean.TRUE.equals(isSingleCheckout)) {
                Order order = new Order();
                order.setAddedDate(new Date());
                order.setStatus(orderDto.getStatus());
                order.setUser(user);
                order.setQuantity(orderDto.getQuantity());
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
                Order order1 = this.orderDao.save(order);
                 this.modelMapper.map(order1, OrderDto.class);
                 return "Order Placed!";
            } else {
                Order order = this.modelMapper.map(orderDto, Order.class);
                order.setAddedDate(new Date());
                order.setStatus(orderDto.getStatus());
                order.setQuantity(orderDto.getQuantity());
                order.setAddressLine1(orderDto.getAddressLine1());
                order.setAddressLine2(orderDto.getAddressLine2());
                order.setCity(orderDto.getCity());
                order.setCountry(order.getCountry());
                order.setState(order.getState());
                order.setUser(user);
                order.setMobileNo(order.getMobileNo());
                order.setQuantity(orderDto.getQuantity());
                order.setZipCode(orderDto.getZipCode());
                order.setTotalPrice(orderDto.getTotalPrice());
                order.setProduct(product);
                Order newCart = this.orderDao.save(order);

                Cart cart = cartDao.findById(user.getCart().getUserCartId()).get();
                cart.getCartDetails().clear();
                cart.setTotalPrice(0);
                cartDao.save(cart);

                this.modelMapper.map(newCart, OrderDto.class);
                return "Order Placed";
            }
        }
//        else{
//            return "User Logged out!";
//        }

    @Transactional
    public void deleteCart(Integer userId){
        User user = userDao.findById(userId).get();
        orderDao.deleteByUser(user);
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
        List<Order> orders = this.orderDao.findAll();
        return orders.stream().map(order -> this.orderToDto(order)).collect(Collectors.toList());
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

    public List<Order> getOrderByUser(Integer userId) {
        User user = this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException(" userCart Id not found with this id "+userId));
       return orderDao.findByUser(user);
    }


    //product to dto fetch
    public OrderDto orderToDto(Order order) {
        OrderDto orderDto = modelMapper.map(order,OrderDto.class);
        return  orderDto;
    }

    public OrderItemDto orderNewDto(Order newOrder) {
        return this.modelMapper.map(newOrder, OrderItemDto.class);
    }
}

