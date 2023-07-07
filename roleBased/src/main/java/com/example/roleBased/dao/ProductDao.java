package com.example.roleBased.dao;

import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.Category;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
//methods
    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE " +
            "p.product_name LIKE CONCAT('%',:query, '%')" +
            "Or p.product_short_desc LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);

    @Query("SELECT p FROM Product p WHERE " +
            "p.product_name LIKE CONCAT('%',:query, '%')")
    List<Product> recommendation(String query);

    List<Product> findBySubCategory(SubCategory subCategory);

}
