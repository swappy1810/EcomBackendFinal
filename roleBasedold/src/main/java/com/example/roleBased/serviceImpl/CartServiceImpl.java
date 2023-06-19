package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CartDao;
import com.example.roleBased.dao.CartDetailDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dto.CartDetailDto;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDetailDao cartDetailDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    ModelMapper modelMapper = new ModelMapper();


    public String addToCart(CartDetails cartDetails, Integer productId) {
      Product product = productDao.findById(productId).get();
        Cart cart = cartDao.findById(cartDetails.getUserCartId()).get();
        List<CartDetails> cartDetails1 = new ArrayList<>();
        cartDetails.setProductId(productId);
        cartDetails1.add(cartDetails);
        cart.setCartDetails(cartDetails1);
        cartDao.save(cart);
        return "Product added to cart";

    }

    private CartDetailDto cartNewDto(CartDetails newCart) {
        return this.modelMapper.map(newCart, CartDetailDto.class);
    }

    private CartDetails DtoToCart(CartDetails cartDetail) {
        return this.modelMapper.map(cartDetail, CartDetails.class);
    }

    public ResponseEntity<ApiResponse> deleteCartById(Integer productId) {
        this.cartDetailDao.deleteById(productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully from cart", true), HttpStatus.OK);
    }

    //get all cart items lists
    public List<CartDetailDto> getCartDetails(Integer userCartId) {
        User user = userDao.findById(userCartId).get();
        return user.getCart().getCartDetails().stream().map(cartDetails -> this.cartNewDto(cartDetails)).collect(Collectors.toList());
    }
    }