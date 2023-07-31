package com.example.roleBased.dao;

import com.example.roleBased.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsDao extends JpaRepository<OrderItems,Integer> {
}
