package com.example.roleBased.dao;

import com.example.roleBased.dto.CartDetailDto;
import com.example.roleBased.entity.CartDetails;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailDao extends JpaRepository<CartDetails,Integer> {

    CartDetails deleteProductByProductId(Product product);

    CartDetailDto deleteProductByUserCartId(Integer userCartId);

}
