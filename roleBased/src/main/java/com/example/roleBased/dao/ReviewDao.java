package com.example.roleBased.dao;

import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao extends JpaRepository<Review, Integer> {
    List<Review> findByproduct(Product product);
}

