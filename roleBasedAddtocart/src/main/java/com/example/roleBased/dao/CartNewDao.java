package com.example.roleBased.dao;

import com.example.roleBased.entity.CartNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartNewDao extends JpaRepository<CartNew,Integer> {




}
