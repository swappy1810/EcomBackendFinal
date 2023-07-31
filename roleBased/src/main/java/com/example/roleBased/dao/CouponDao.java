package com.example.roleBased.dao;

import com.example.roleBased.entity.Coupon;
import com.example.roleBased.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponDao extends JpaRepository<Coupon,Integer>{

   // Coupon findBySubCatId(Integer subCatId);
   List<Coupon> findByTotalOrderPrice(double totalOrderPrice);
    Coupon findByCouponCode(String couponCode);

}
