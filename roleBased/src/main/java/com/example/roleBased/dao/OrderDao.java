package com.example.roleBased.dao;

import com.example.roleBased.entity.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {

    List<Order> findByUserId(Integer userId);

    public void deleteByUserId(Integer userId);

    List<Order> findByUserIdOrderByAddedDateAsc(Integer userId);

}
