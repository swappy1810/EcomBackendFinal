package com.example.roleBased.controller;

import com.example.roleBased.dao.SubCategoryDao;
import com.example.roleBased.entity.Coupon;
import com.example.roleBased.exception.ApiResponse;
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


    //update product by product id
  /*  @PutMapping("/{couponId}")
    public ResponseEntity<Coupon> updateCoupon(@Valid @RequestBody Coupon coupon, @PathVariable Integer couponId){
        Coupon updateCoupon = this.couponService.updateCoupon(coupon,couponId);
        updateCoupon.setSubCatId(coupon.getSubCatId());
        updateCoupon.setDiscountedPrice(coupon.getDiscountedPrice());
        return ResponseEntity.ok(updateCoupon);
    }*/

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

   /* @GetMapping("/random/{userId}")
    public Coupon getRandomCoupon(@PathVariable Integer userId){
        return couponService.getSubcategory(userId);
    }*/

    @GetMapping("/applyCoupon/{couponCode}/{orderPrice}")
    public double applyCoupon(@PathVariable String couponCode,
                               @PathVariable double orderPrice){
        System.out.println(couponCode);
        return couponService.applyCoupon(couponCode,orderPrice);
    }

    @PostMapping("/addCoupon/{orderPrice}")
    public String createCoupon(@Valid @RequestBody Coupon couponDto, @PathVariable double orderPrice){
        this.couponService.createCoupon(couponDto,orderPrice);
        return "Coupons are created";
    }

    //get coupon by orderPrice
    @GetMapping("/getcoupon/{orderPrice}")
    public ResponseEntity<List<Coupon>> getcouponByOrderPrice(@PathVariable double orderPrice) {
        return ResponseEntity.ok(this.couponService.getCoponByPrice(orderPrice));
    }

}
