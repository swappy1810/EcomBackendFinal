package com.example.roleBased.dao;

import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import com.example.roleBased.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListDao  extends JpaRepository<Wishlist,Integer>
{
    //Method for fetching the wishlist of a particular user and order it by created_date
    //List<WishList> findAllByUserIdOrderByCreatedDateDesc(Integer userId);
    List<Wishlist> findByUserId(Integer userId);

    public void deleteByProduct(Product Product);

}
