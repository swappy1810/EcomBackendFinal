package com.example.roleBased.dao;

import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.Category;
import com.example.roleBased.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
//methods
    List<Product> findByCategory(Category category);

//   @Query(value="select p from products p where p.product_name like CONCAT('%',:search_text,'%') or p.product_description like CONCAT('%',:search_text,'%') or p.product_category like CONCAT('%',:search_text,'%')", nativeQuery=true)
@Query(value = "SELECT * FROM product p WHERE " +
        "p.product_name LIKE CONCAT('%',:query, '%')" +
"Or p.product_short_desc LIKE CONCAT('%',:query,'%')",nativeQuery = true)
//@Query(value = "SELECT p FROM product p WHERE CONCAT(p.product_name,p.product_short_desc) LIKE %?1%",nativeQuery = true)
    List<Product> findBySearchText(@Param("query") String searchText);

}