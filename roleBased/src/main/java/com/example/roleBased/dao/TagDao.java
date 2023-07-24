package com.example.roleBased.dao;

import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao extends JpaRepository<Tags,Integer> {

    List<Tags> findByProduct(Product product);

}
