package com.example.roleBased.dao;

import com.example.roleBased.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends JpaRepository<Tags,Integer> {
}
