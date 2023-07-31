package com.example.roleBased.dao;

import com.example.roleBased.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
//methods

    List<Order> findByProduct(Product product);

    List<Order> findByUser(User user);

    public void deleteByUser(User user);


}
