package com.example.roleBased.controller;

import com.example.roleBased.dao.SubCategoryDao;
import com.example.roleBased.dto.CouponDto;
import com.example.roleBased.dto.SubCatDto;
import com.example.roleBased.entity.Coupon;
import com.example.roleBased.entity.SubCategory;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.exception.ResourceNotFoundException;
import com.example.roleBased.serviceImpl.CouponServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("coupon")
public class CouponController {

    @Autowired
    private CouponServiceImpl couponService;

    @Autowired
    private SubCategoryDao subCategoryDao;

    //add product to products
    @PostMapping("/save/{subCatId}")
    public ResponseEntity<Coupon> createCoupon(@Valid @RequestBody Coupon couponDto, @PathVariable("subCatId") Integer subCatId){
        Coupon coupon1 = this.couponService.createCoupon(couponDto,subCatId);
        return new ResponseEntity<>(coupon1, HttpStatus.CREATED);
    }

    //update product by product id
    @PutMapping("/{couponId}")
    public ResponseEntity<Coupon> updateCoupon(@Valid @RequestBody Coupon coupon, @PathVariable Integer couponId){
        Coupon updateCoupon = this.couponService.updateCoupon(coupon,couponId);
        updateCoupon.setSubCatId(coupon.getSubCatId());
        updateCoupon.setDiscountedPrice(coupon.getDiscountedPrice());
        updateCoupon.setUserId(coupon.getUserId());
        return ResponseEntity.ok(updateCoupon);
    }

    //delete the product by product Id
    @DeleteMapping("/{couponId}")
    public ResponseEntity<ApiResponse> deleteCoupon(@PathVariable Integer couponId){
        this.couponService.deleteCoupon(couponId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Coupon code Deleted Successfully",true), HttpStatus.OK);
    }

    //get all product
    @GetMapping("/")
    public ResponseEntity<List<Coupon>> getAllCoupon(){
        return ResponseEntity.ok(this.couponService.getAllCoupons());
    }

    //get product by product id
    @GetMapping("/{couponId}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Integer couponId){
        return ResponseEntity.ok(this.couponService.getCouponById(couponId));
    }

    @GetMapping("/random/{userId}")
    public Coupon getRandomCoupon(@PathVariable Integer userId){
        return couponService.getSubcategory(userId);
    }

}
