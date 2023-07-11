package com.example.roleBased.dao;

import com.example.roleBased.dto.CartDetailDto;
import com.example.roleBased.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailDao extends JpaRepository<CartDetails,Integer> {

    CartDetails deleteByProduct(Product product);

    CartDetailDto deleteProductByUserId(Integer userId);

    Product findByProduct(Product product);

    List<CartDetails> findByUserCartId(Integer userCartId);

    List<CartDetails> findByUserId(Integer userId);

    //List<CartDetails> findByUserCartId(Integer userCartId);

}
