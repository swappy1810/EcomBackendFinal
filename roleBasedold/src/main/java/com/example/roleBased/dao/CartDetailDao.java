package com.example.roleBased.dao;

import com.example.roleBased.entity.Cart;
import com.example.roleBased.entity.CartDetails;
import com.example.roleBased.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailDao extends JpaRepository<CartDetails,Integer> {

    CartDetails deleteProductByProductId(Product product);

}
