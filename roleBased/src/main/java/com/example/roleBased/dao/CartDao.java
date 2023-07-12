package com.example.roleBased.dao;

import com.example.roleBased.entity.Cart;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {

    Cart findByUserCartId(Integer userCartId);

}
