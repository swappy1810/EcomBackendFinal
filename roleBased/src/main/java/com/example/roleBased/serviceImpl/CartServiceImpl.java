package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CartDao;
import com.example.roleBased.dao.CartDetailDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dto.CartDetailDto;
import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartServiceImpl {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDetailDao cartDetailDao;

    @Autowired
    private CartDao cartDao;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserDao userDao;

    public String addToCart(CartDetails cartDetails, Integer productId, Integer userId) {
        User user = this.userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with this id" + userId));
        if (user != null) {
            Product product = productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id" + productId));
            Cart cart = cartDao.findById(user.getCart().getUserCartId()).orElseThrow(() -> new ResourceNotFoundException("usercart Id not found with this id" + user.getCart().getUserCartId()));
            List<CartDetails> cartDetails1 = new ArrayList<>();
            cartDetails.setProduct(product);
            cartDetails.setUserId(cartDetails.getUserId());
            cartDetails.setUserCartId(cart.getUserCartId());
            cartDetails.setPrice(cartDetails.getQuantity() * product.getProduct_price());
            cartDetails.setQuantity(cartDetails.getQuantity());
            cartDetails1.add(cartDetails);
            cart.setCartDetails(cartDetails1);
            cartDao.save(cart);
            return "Product added to cart";
        }
        return "User not logged In please sign In";
    }

    public void clearCart(Cart cart) {
        cart.setCartDetails(new ArrayList<>());
        cart.setTotalPrice(0.0);
        cartDao.save(cart);
    }

    public ResponseEntity<ApiResponse> deleteCartById(Integer productId,Integer userId) {
        User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id"+userId));
        Product product = productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with this id" + productId));
        List<CartDetails> cartDetails1 = cartDetailDao.findByProduct(product);
        for(CartDetails cartDetailsNew : cartDetails1) {
            if (userId == cartDetailsNew.getUserId()) {
                cartDetailDao.deleteById(cartDetailsNew.getId());
            }
        }
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully from cart", true), HttpStatus.OK);
    }

    //get all cart items lists
    public List<CartDetails> getCartDetails(Integer userId) {
        User user = userDao.findById(userId).get();
        return cartDetailDao.findByUserId(userId);
    }

    @Transactional
    public ResponseEntity<ApiResponse> deleteCartByUserId(Integer userId){
        User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user id not found with this id"+userId));
        this.cartDetailDao.deleteByUserId(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("cart deleted successfully",true),HttpStatus.OK);
        }

    public Cart getCartById(Integer userCartId){
        return cartDao.findByUserCartId(userCartId);
    }

    public List<CartDetails> getCartItemByCartId(Integer cartId){
        Cart cart = cartDao.findById(cartId).orElseThrow(()->new ResourceNotFoundException("cart id not found with this id"+cartId));
        if(cart != null) {
            return cart.getCartDetails();
        }
        return Collections.emptyList();
    }

    //update cart method
    public ResponseEntity<String> updateCart(CartDetails cartDetails,Integer quantity, Integer productId,Integer userId,boolean productExistInCart) {
        User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id"+userId));
        Product product = productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("product not found with this id"+productId));
        cartDetails.setUserId(cartDetails.getUserId());
        List<CartDetails> cartDetailsList = getCartDetails(userId);
        List<CartDetails> cartDetails1 = cartDetailDao.findByProduct(product);
        for(CartDetails cartDetailsNew : cartDetails1){
        for(CartDetails cartDetails2 : cartDetailsList) {
            if (userId == cartDetailsNew.getUserId() && productExistInCart == true) {
                cartDetailsNew.setQuantity(quantity);
                cartDetailsNew.setPrice(product.getProduct_price() * quantity);
                cartDetailDao.save(cartDetailsNew);
                break;
            }
        }
        }
        if(!productExistInCart){
            CartDetails cartDetails2 = new CartDetails();
            cartDetails2.setProduct(product);
            cartDetails2.setQuantity(quantity);
            cartDetailsList.add(cartDetails2);
            cartDetailDao.saveAll(cartDetailsList);
        }
        return ResponseEntity.ok("Cart updated successfully");
    }
}