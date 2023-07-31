package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.*;
import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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


    public String placeOrder(OrderItems orderItems1, Integer userId, Integer quantity1, Integer productId) {
        User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with this id" + userId));
        Product product1 = productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product id not found with this id" + productId));
        List<CartDetails> cartDetailsList = cartDetailDao.findByUserId(userId);
        Order order1 = new Order();
        order1.setUserId(userId);
        order1.setAddedDate(LocalDate.now());
        order1.setShippingDate(order1.getAddedDate().plusDays(7));
        order1.setPrice(product1.getProduct_price()*quantity1);
        List<OrderItems> orderdDetailsList = new ArrayList<>();
        OrderItems orderItems = new OrderItems();
           if (product1.getQuantity() < quantity1) {
                throw new ResourceNotFoundException("Product Out of Stock!");
            } else {
                product1.setQuantity((product1.getQuantity() - quantity1));
                productDao.save(product1);
            }
            orderItems.setAddedDate(LocalDate.now());
            orderItems.setShippingDate(order1.getAddedDate().plusDays(7));
            orderItems.setQuantity(quantity1);
            orderItems.setAddressLine1(orderItems1.getAddressLine1());
            orderItems.setAddressLine2(orderItems1.getAddressLine2());
            orderItems.setCity(orderItems1.getCity());
            orderItems.setCountry(orderItems1.getCountry());
            orderItems.setState(orderItems1.getState());
            orderItems.setMobileNo(orderItems1.getMobileNo());
            orderItems.setZipCode(orderItems1.getZipCode());
            orderItems.setTotalPrice(product1.getProduct_price() * quantity1);
            orderItems.setProduct(product1);
            orderdDetailsList.add(orderItems);

        order1.setOrderItems(orderdDetailsList);
        orderDao.save(order1);
        Cart cart = cartDao.findById(user.getCart().getUserCartId()).get();
        cart.getCartDetails().clear();
        cartDao.save(cart);
        return "Order Placed";
    }

        public String createOrder(OrderItems orderItems1, Integer userId) {
            User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with this id" + userId));
            List<CartDetails> cartDetailsList = cartDetailDao.findByUserId(userId);
            if (cartDetailsList != null) {
                Order order1 = new Order();
                order1.setUserId(userId);
                order1.setAddedDate(LocalDate.now());
                order1.setShippingDate(order1.getAddedDate().plusDays(7));
                List<OrderItems> orderdDetailsList = new ArrayList<>();
                    int index;
                double total =0;
                    for (index = 0; index < cartDetailsList.size(); index++) {
                        OrderItems orderItems = new OrderItems();
                        CartDetails cartDetails = cartDetailsList.get(index);
                        Product product = productDao.findById(cartDetails.getProduct().getProduct_id()).get();
                        int quantity = cartDetails.getQuantity();
                        total += product.getProduct_price() * quantity;
                        if (product.getQuantity() < quantity) {
                            throw new ResourceNotFoundException("some products are out of stock can't place order!\"");
                        } else {
                            product.setQuantity((product.getQuantity() - quantity));
                            productDao.save(product);
                        }
                        orderItems.setAddedDate(LocalDate.now());
                        orderItems.setShippingDate(order1.getAddedDate().plusDays(7));
                        orderItems.setQuantity(cartDetails.getQuantity());
                        orderItems.setAddressLine1(orderItems1.getAddressLine1());
                        orderItems.setAddressLine2(orderItems1.getAddressLine2());
                        orderItems.setCity(orderItems1.getCity());
                        orderItems.setCountry(orderItems1.getCountry());
                        orderItems.setState(orderItems1.getState());
                        orderItems.setMobileNo(orderItems1.getMobileNo());
                        orderItems.setZipCode(orderItems1.getZipCode());
                        orderItems.setProduct(product);
                        orderItems.setTotalPrice(cartDetails.getPrice() * cartDetails.getQuantity());
                        orderdDetailsList.add(orderItems);
                    }
                    order1.setPrice(total);
                    order1.setOrderItems(orderdDetailsList);
                    orderDao.save(order1);
                    Cart cart = cartDao.findById(user.getCart().getUserCartId()).get();
                    cart.getCartDetails().clear();
                    cartDao.save(cart);
                    return "Order Placed";
                } else {
                    return "cart is empty cannot placed order";
                }
        }


    @Transactional
    public void deleteCart(Integer userId){
        User user = userDao.findById(userId).get();
        orderDao.deleteByUserId(userId);
    }

    //update the order by order id
    public Order updateOrder(Order orderDto, Integer orderId) {
        Order order = this.orderDao.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with this id " + orderId));
        order.setUserId(orderDto.getUserId());
        order.setPrice(orderDto.getPrice());
        order.setAddedDate(LocalDate.now());
        return orderDao.save(order);
    }

    //delete order by order id
    public Order deleteOrder(Integer orderId) {
        Order order =  orderDao.findById(orderId).orElseThrow(()->new ResourceNotFoundException("order id not found with this id"+orderId));
        return orderDao.deleteById(order);
    }

    //get all order
    public List<Order> getAllOrder() {
        List<Order> orders = this.orderDao.findAll();
        return orders.stream().map(order -> (order)).collect(Collectors.toList());
    }

    //get order by order id
    public Order getOrderById(Integer orderId) {
        return orderDao.findById(orderId).orElseThrow(()->new ResourceNotFoundException("order id not found with this id"+orderId));
    }

//get order by user by user id
    public List<Order> getOrderByUser(Integer userId) {
        User user = this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException(" userCart Id not found with this id "+userId));
       return orderDao.findByUserIdOrderByAddedDateAsc(userId);
    }

}

