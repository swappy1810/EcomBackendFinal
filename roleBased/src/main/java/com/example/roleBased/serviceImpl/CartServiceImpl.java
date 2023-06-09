package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CartDao;
import com.example.roleBased.dao.CartNewDao;
import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.UserDao;
import com.example.roleBased.dto.CartDto;
import com.example.roleBased.dto.CartNewDto;
import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CartServiceImpl {

    // @Autowired
    //private CartDao cartDao;
    @Autowired
    private CartNewDao cartNewDao;



    @Autowired
    private ProductDao productDao;

    // @Autowired
    //private UserDao userDao;

    ModelMapper modelMapper = new ModelMapper();


    public CartNewDto addToCart(@RequestBody CartNewDto cartNewDto, @PathVariable Integer productId) {

        Product product = productDao.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id" + productId));
        CartNew cart = new CartNew();
        cart.setQuantity(cartNewDto.getQuantity());
        cart.setTotalPrice(cartNewDto.getTotalPrice());
        cart.setUserid(cartNewDto.getUserid());
        //  cart.setupCartId(cartNewDto.getUpCartId());
        cart.setProduct(product);
        CartNew newCart = this.cartNewDao.save(cart);
        CartNewDto cartNewDto1 = this.cartNewDto(newCart);
        return cartNewDto1;
    }

    private CartNewDto cartNewDto(CartNew newCart) {
        CartNewDto cartNewDto = this.modelMapper.map(newCart, CartNewDto.class);
        return cartNewDto;
    }
    public ResponseEntity<CartNewDto> deleteCart(Integer cart_newId) {
        CartNew cartNew= cartNewDao.findById(cart_newId).orElseThrow(() -> new ResourceNotFoundException("Cart not found with this id " + cart_newId));
        this.cartNewDao.deleteById(cart_newId);
        return null;
    }

}

    /*public List<Cart> getCartDetails(){
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username).get();
        return cartDao.findByUser(user);
    }*/

// public List<Cart> CartDetails(User user_username){
        /*Product product = productDao.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found with this id"+productId));
        if (product!=null){
            return cartDao.findById(productId);

        }
        return null;*/
      /*  if(user_username!=null){
            String name=user_username.getUsername();
            return cartDao.findAllByuser_username(name);

        }
        return  null;
    }*/

    /*private CartDto CartToDto(Cart cart){
        CartDto cartDto= this.modelMapper.map(cart, CartDto.class);
        return cartDto;

    }*/


