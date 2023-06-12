package com.example.roleBased.dao;

import com.example.roleBased.dto.WishListDto;
import com.example.roleBased.entity.Order;
import com.example.roleBased.entity.User;
import com.example.roleBased.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListDao extends JpaRepository<WishList,Integer> {


        //Method for fetching the wishlist of a particular user and order it by created_date
        //List<WishList> findAllByUserIdOrderByCreatedDateDesc(Integer userId);
         List<WishList> findByUser(User user);

    }
