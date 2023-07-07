package com.example.roleBased.dao;

import com.example.roleBased.entity.Coupon;
import com.example.roleBased.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponDao extends JpaRepository<Coupon,Integer>{

    Coupon findBySubCatId(Integer subCatId);

}
