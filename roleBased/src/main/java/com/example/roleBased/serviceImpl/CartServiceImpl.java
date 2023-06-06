package com.example.roleBased.serviceImpl;

import com.example.roleBased.config.JwtRequestFilter;
import com.example.roleBased.config.JwtUtil;
import com.example.roleBased.dao.CartDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.entity.Cart;
import com.example.roleBased.entity.JwtRequest;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl{

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public String addToCart(Integer productId) {
        Product product = productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found with this id"+productId));
        String username = JwtRequestFilter.CURRENT_USER;
        User user = null;
        if (user != null) {
            user = userDao.getUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Username not found" + username));
        }
        if (product != null && user != null) {
            Cart cart = new Cart(product, user);
             cartDao.save(cart);
        }

        return "Added product to cart";
    }

    public List<Cart> getCartDetails(){
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username).get();
        return cartDao.findByUser(user);
    }

}

