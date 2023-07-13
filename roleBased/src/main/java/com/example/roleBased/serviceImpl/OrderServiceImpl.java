package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.*;
import com.example.roleBased.dto.OrderDto;
import com.example.roleBased.dto.OrderItemDto;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


    public String placeOrder(OrderItems orderItems1, Integer userId, boolean isSingleCheckout,double price){
        User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id"+userId));
//        Cart id= user.getCart();
//        cartService.clearCart(id);
        List<CartDetails> cartDetailsList = cartDetailDao.findByUserId(userId);
        Order order1 = new Order();
        order1.setUserId(userId);
        order1.setAddedDate(new Date());
        order1.setPrice(price);
        List<OrderItems> orderdDetailsList = new ArrayList<>();
        int index;
        for(index=0;index<cartDetailsList.size();index++) {
            OrderItems orderItems = new OrderItems();
            CartDetails cartDetails = cartDetailsList.get(index);
            Product product = productDao.findById(cartDetails.getProduct().getProduct_id()).get();
            if (Boolean.TRUE.equals(isSingleCheckout)) {
                orderItems.setAddedDate(new Date());
                orderItems.setStatus(orderItems1.getStatus());
                orderItems.setQuantity(cartDetails.getQuantity());
                orderItems.setAddressLine1(orderItems1.getAddressLine1());
                orderItems.setAddressLine2(orderItems1.getAddressLine2());
                orderItems.setCity(orderItems1.getCity());
                orderItems.setCountry(orderItems1.getCountry());
                orderItems.setState(orderItems1.getState());
                orderItems.setMobileNo(orderItems1.getMobileNo());
                orderItems.setZipCode(orderItems1.getZipCode());
                orderItems.setTotalPrice(cartDetails.getPrice() * cartDetails.getQuantity());
                orderItems.setProductId(cartDetails.getProduct().getProduct_id());
                orderdDetailsList.add(orderItems);
            } else {
                orderItems.setAddedDate(new Date());
                orderItems.setStatus(orderItems1.getStatus());
                orderItems.setQuantity(cartDetails.getQuantity());
                orderItems.setAddressLine1(orderItems1.getAddressLine1());
                orderItems.setAddressLine2(orderItems1.getAddressLine2());
                orderItems.setCity(orderItems1.getCity());
                orderItems.setCountry(orderItems1.getCountry());
                orderItems.setState(orderItems1.getState());
                orderItems.setMobileNo(orderItems1.getMobileNo());
                orderItems.setZipCode(orderItems1.getZipCode());
                orderItems.setProductId(cartDetails.getProduct().getProduct_id());
                orderItems.setTotalPrice(cartDetails.getPrice() * cartDetails.getQuantity());
                orderdDetailsList.add(orderItems);
            }
        }
        order1.setOrderItems(orderdDetailsList);
        orderDao.save(order1);
        Cart cart = cartDao.findById(user.getCart().getUserCartId()).get();
        System.out.println(cart);
        cart.getCartDetails().clear();
        System.out.println(cart);
        cartDao.save(cart);
        return "Order Placed";
    }

    @Transactional
    public void deleteCart(Integer userId){
        User user = userDao.findById(userId).get();
        orderDao.deleteByUserId(userId);
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

//get order by user by user id
    public List<Order> getOrderByUser(Integer userId) {
        User user = this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException(" userCart Id not found with this id "+userId));
       return orderDao.findByUserIdOrderByAddedDateAsc(userId);
    }
}

