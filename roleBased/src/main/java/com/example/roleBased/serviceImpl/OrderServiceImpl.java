package com.example.roleBased.serviceImpl;

import com.example.roleBased.config.JwtRequestFilter;
import com.example.roleBased.dao.*;
import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemsDao orderItemsDao;

    @Autowired
    private CartDetailDao cartDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CouponServiceImpl couponService;

    @Autowired
    private CartDao cartDao;

    @Autowired
            private CartServiceImpl cartService;


//    public String addOrder(CartDetails cartDetails, Integer userId, boolean isSingleCheckout, Integer quantity) {
//        User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user id not found with this id" + userId));
//        Cart cart = new Cart();
//        List<CartDetails> cartDetails1 = new ArrayList<>();
//        OrderItems orderItems = new OrderItems();
//        List<Order> orders = new ArrayList<>();
//        for (CartDetails cartDetails2 : cartDetails1) {
//            Product product = cartDetails2.getProduct();
//            int quantity1 = cartDetails2.getQuantity();
//            double totalPrice = product.getProduct_price()*cartDetails2.getQuantity();
//
//            //int discountedPrice = couponService.applyCoupon(couponCode, product.getProduct_price());
//            if (product.getQuantity() <= 0) {
//                throw new ResourceNotFoundException("Product Out of Stock!");
//            } else {
//                product.setQuantity((product.getQuantity() - quantity));
//                productDao.save(product);
//            }
//            if (Boolean.TRUE.equals(isSingleCheckout)) {
//                Order order1 = new Order();
//                order1.setProduct(product);
//                order1.setQuantity(quantity);
//                order1.setTotalPrice(product.getProduct_price() * order1.getQuantity());
//                order1.setUser(user);
//                order1.setStatus(order1.getStatus());
//                order1.setAddressLine1(order1.getAddressLine1());
//                order1.setAddressLine2(order1.getAddressLine2());
//                order1.setAddedDate(new Date());
//                order1.setCity(order1.getCity());
//                order1.setCountry(order1.getCountry());
//                order1.setState(order1.getState());
//                order1.setMobileNo(order1.getMobileNo());
//                order1.setZipCode(order1.getZipCode());
//                orderItems.getOrders().add(order1);
//                orders.add(order1);
//                orderDao.saveAll(orders);
//                return "Order Placed";
//            } else {
//                Order order1 = new Order();
//                order1.setProduct(product);
//                order1.setQuantity(quantity);
//                order1.setTotalPrice(product.getProduct_price() * order1.getQuantity());
//                order1.setUser(user);
//                order1.setStatus(order1.getStatus());
//                order1.setAddressLine1(order1.getAddressLine1());
//                order1.setAddressLine2(order1.getAddressLine2());
//                order1.setAddedDate(new Date());
//                order1.setCity(order1.getCity());
//                order1.setCountry(order1.getCountry());
//                order1.setState(order1.getState());
//                order1.setMobileNo(order1.getMobileNo());
//                order1.setZipCode(order1.getZipCode());
//                orderItems.getOrders().add(order1);
//                orders.add(order1);
//                orderDao.saveAll(orders);
//                return "Order Placed";
//            }
//        }
//        cartService.clearCart(cart);
//        return "some error for order placing";
//    }

//add order to orders
public String createOrder(OrderDto order, Integer productId, Boolean isSingleCheckout, Integer userId,Integer quantity) {
//        if(JwtRequestFilter.CURRENT_USER!=null) {
    User user = this.userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with user Id" + userId));
    Product product = this.productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with this id" + productId));
    if(product.getQuantity()<quantity){
        throw new ResourceNotFoundException("Product Out of Stock!");
    }
    else {
        product.setQuantity((product.getQuantity() - quantity));
        productDao.save(product);
    }
    if (Boolean.TRUE.equals(isSingleCheckout)) {

        Order order1 = new Order();
        order1.setAddedDate(new Date());
        order1.setStatus(order.getStatus());
        order1.setUser(user);
        order1.setQuantity(order.getQuantity());
        order1.setAddressLine1(order.getAddressLine1());
        order1.setAddressLine2(order.getAddressLine2());
        order1.setCity(order.getCity());
        order1.setCountry(order.getCountry());
        order1.setState(order.getState());
        order1.setMobileNo(order.getMobileNo());
        order1.setQuantity(quantity);
        order1.setZipCode(order.getZipCode());
        order1.setTotalPrice(order.getTotalPrice());
        order1.setProduct(product);
       orderDao.save(order1);
        return "Order Placed!";
    } else {
        Order order2 = new Order();
        order2.setAddedDate(new Date());
        order2.setStatus(order.getStatus());
        order2.setQuantity(order.getQuantity());
        order2.setAddressLine1(order.getAddressLine1());
        order2.setAddressLine2(order.getAddressLine2());
        order2.setCity(order.getCity());
        order2.setCountry(order.getCountry());
        order2.setState(order.getState());
        order2.setUser(user);
        order2.setMobileNo(order.getMobileNo());
        order2.setQuantity(quantity);
        order2.setZipCode(order.getZipCode());
        order2.setTotalPrice(order.getTotalPrice());
        order2.setProduct(product);
        this.orderDao.save(order2);

        Cart cart = cartDao.findById(user.getCart().getUserCartId()).get();
        cart.getCartDetails().clear();
        cart.setTotalPrice(0);
        cartDao.save(cart);
        return "Order Placed";
    }
}

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
    public List<Order> getAllOrder() {
        List<Order> orders = this.orderDao.findAll();
        return orders.stream().map(order -> (order)).collect(Collectors.toList());
    }

    //get order by order id

    public OrderDto getOrderById(Integer orderId) {
        return null;
    }

//get order by product by product id

    public List<Order> getOrderByProduct(Integer productId) {
        Product product= this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product with cart not found with this id "+productId));
        List<Order> orders = this.orderDao.findByProduct(product);
        List<Order> orderDtos = orders.stream().map((x) -> x).collect(Collectors.toList());
        return orderDtos;
    }

//get order by user by user id

    public List<Order> getOrderByUser(Integer userId) {
        User user = this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException(" userCart Id not found with this id "+userId));
       return orderDao.findByUser(user);
    }
}

