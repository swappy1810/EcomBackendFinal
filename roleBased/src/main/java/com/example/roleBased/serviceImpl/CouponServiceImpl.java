package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.*;
import com.example.roleBased.dto.CouponDto;
import com.example.roleBased.dto.SubCatDto;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private SubCategoryDao subCategoryDao;

    ModelMapper modelMapper = new ModelMapper();

    //create Coupon add method
    public Coupon createCoupon(Coupon coupon, Integer subCatId) {
        SubCategory subCategory = subCategoryDao.findById(subCatId).orElseThrow(()->new ResourceNotFoundException("Subcategory not found with this id"+subCatId));
        coupon.setSubCatId(subCatId);
        coupon.setExpiryDate(new Date());
        return this.couponDao.save(coupon);
    }

    //update Coupon method
    public Coupon updateCoupon(Coupon coupon, Integer couponId) {
        Coupon coupon1 = this.couponDao.findById(couponId).orElseThrow(() -> new ResourceNotFoundException("Coupon not found with this id " + couponId));
        coupon1.setCouponCode(coupon.getCouponCode());
        coupon1.setDiscountedPrice(coupon.getDiscountedPrice());
        coupon1.setExpiryDate(new Date());
        coupon1.setSubCatId(coupon.getSubCatId());
        Coupon updateCoupon = this.couponDao.save(coupon);
        return updateCoupon;
    }

    //get coupon by couponId Id method
    public Coupon getCouponById(Integer couponId) {
        Coupon coupon = couponDao.findById(couponId).orElseThrow(() -> new ResourceNotFoundException("Coupon not found with this id " + couponId));
        return coupon;
    }

    //get all coupon method
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = this.couponDao.findAll();
        List<Coupon> coupons1= coupons.stream().map(coupon ->coupon).collect(Collectors.toList());
        return coupons1;
    }

    //delete coupon by coupon id method
    public ResponseEntity<Coupon> deleteCoupon(Integer couponId) {
        Coupon coupon = couponDao.findById(couponId).orElseThrow(() -> new ResourceNotFoundException("Coupon not found with this id " + couponId));
        this.couponDao.deleteById(couponId);
        return null;
    }

    public Coupon getSubcategory(Integer productId){
        Product product = productDao.findById(productId).orElseThrow(()-> new ResourceNotFoundException("User not found with this id"+productId));
        SubCategory subCategory =product.getSubCategory();
        int id = subCategory.getSubCatId();
        return couponDao.findBySubCatId(id);
    }

    public Integer applyCoupon(String couponCode,
                                                 Integer price){
     Coupon coupon = couponDao.findByCouponCode(couponCode);

        if(coupon == null) {
           return null;
        }
        if(coupon.getExpiryDate().before(new Date())){
            return null;
        }
        int discountedPrice = price - coupon.getDiscountedPrice();
        return discountedPrice;
    }

}
