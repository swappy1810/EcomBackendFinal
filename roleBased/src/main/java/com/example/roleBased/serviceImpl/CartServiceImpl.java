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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceImpl {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDetailDao cartDetailDao;

    @Autowired
    private CartDao cartDao;

    ModelMapper modelMapper= new ModelMapper();

    @Autowired
    private UserDao userDao;

    public String addToCart(CartDetails cartDetails, Integer productId,Integer userId) {
        User user = this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with this id"+userId));
        if(user != null) {
            Product product = productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found with this id"+productId));
            Cart cart = cartDao.findById(cartDetails.getUserCartId()).orElseThrow(()->new ResourceNotFoundException("usercart Id not found with this id"+cartDetails.getUserCartId()));
            List<CartDetails> cartDetails1 = new ArrayList<>();
            cartDetails.setProduct(product);
            cartDetails.setUserId(userId);
            cartDetails.setUserCartId(cartDetails.getUserCartId());
            cartDetails.setPrice(cartDetails.getQuantity()*product.getProduct_price());
            cartDetails.setQuantity(cartDetails.getQuantity());
            cartDetails1.add(cartDetails);
            cart.setCartDetails(cartDetails1);
            cartDao.save(cart);
            return "Product added to cart";
        }
        else{
            return "User not logged In please sign In!";
        }
    }

    public void clearCart(Cart cart){
        cart.setCartDetails(new ArrayList<>());
        cart.setTotalPrice(0.0);
        cartDao.save(cart);
    }

    public ResponseEntity<ApiResponse> deleteCartById(Integer productId) {
        this.cartDetailDao.deleteById(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully from cart", true), HttpStatus.OK);
    }

    //get all cart items lists
    public List<CartDetails> getCartDetails(Integer userId) {
        User user = userDao.findById(userId).get();
        return cartDetailDao.findByUserId(userId);
        }

        //update cart method
    public CartDetails updateCart(CartDetails cartDetails, Integer cartId,Integer productId) {
        Product product = this.productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("product not found with this id"+productId));
        CartDetails cartDetails1 = this.cartDetailDao.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cart id not found with this id " + cartId));
        cartDetails1.setQuantity(cartDetails.getQuantity());
        CartDetails updateCart = this.cartDetailDao.save(cartDetails1);
        return updateCart;
    }


}