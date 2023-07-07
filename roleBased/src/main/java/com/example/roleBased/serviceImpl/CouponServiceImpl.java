package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CouponDao;
import com.example.roleBased.dao.SubCategoryDao;
import com.example.roleBased.dto.CouponDto;
import com.example.roleBased.dto.SubCatDto;
import com.example.roleBased.entity.Coupon;
import com.example.roleBased.entity.SubCategory;
import com.example.roleBased.entity.User;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private SubCategoryDao subCategoryDao;

    ModelMapper modelMapper = new ModelMapper();

    //create Coupon add method
    public Coupon createCoupon(Coupon coupon, Integer subCatId) {
        SubCategory subCategory = subCategoryDao.findById(subCatId).orElseThrow(()->new ResourceNotFoundException("Subcategory not found with this id"+subCatId));
//        Coupon coupon= this.dtoToCoupon(couponDto);
        coupon.setSubCategory(subCategory);
        coupon.setUserId(coupon.getUserId());
        Coupon savedCoupon = this.couponDao.save(coupon);
//        CouponDto couponDto1 = this.couponToDto(savedCoupon);
        return savedCoupon;
    }

    public User givenList_shouldReturnRandomSeries(SubCategory subCategory) {
        List<SubCategory> givenList = new ArrayList<>();
        Collections.shuffle(givenList);

        List<SubCategory> randomSeries = givenList.subList(0, givenList.size());
        return null;
    }

    //update Coupon method
    public Coupon updateCoupon(Coupon coupon, Integer couponId) {
        Coupon coupon1 = this.couponDao.findById(couponId).orElseThrow(() -> new ResourceNotFoundException("Coupon not found with this id " + couponId));
        coupon1.setCouponCode(coupon.getCouponCode());
        coupon1.setDiscountedPrice(coupon.getDiscountedPrice());
        coupon1.setUserId(coupon.getUserId());
        coupon1.setSubCategory(coupon.getSubCategory());
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

    //dto to subCat fetch
    private Coupon dtoToCoupon(CouponDto coupon) {
        Coupon coupon1 = modelMapper.map(coupon,Coupon.class);
        return coupon1;
    }

    //subCat to dto fetch
    private CouponDto couponToDto(Coupon coupon) {
        CouponDto couponDto = modelMapper.map(coupon,CouponDto.class);
        return  couponDto;
    }

}
