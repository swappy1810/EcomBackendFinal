package com.example.roleBased.dao;

import com.example.roleBased.entity.Order;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
//methods
    List<Order> findByUserCartId(Integer userCartId);

    List<Order> findByProduct(Product product);

}
