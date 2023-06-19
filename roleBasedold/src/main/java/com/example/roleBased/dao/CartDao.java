package com.example.roleBased.dao;

import com.example.roleBased.entity.Cart;
import com.example.roleBased.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {

}
