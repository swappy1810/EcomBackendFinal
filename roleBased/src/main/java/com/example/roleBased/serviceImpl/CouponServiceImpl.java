package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.*;
import com.example.roleBased.entity.*;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public String createCoupon(Coupon coupon, double orderPrice) {
        ArrayList<Coupon> couponList = new ArrayList<>();
        if (orderPrice>5000&& orderPrice<=10000){
            for(int i=1 ; i<=3;i++) {
                if(i==1) {
                   Coupon coupon1=new Coupon();
                    coupon1.setCouponCode("XY200AB");
                    coupon1.setDiscountedPrice(20);
                    coupon1.setTotalOrderPrice(orderPrice);
                    couponList.add(coupon1);
                }
                else if(i==2) {
                    Coupon coupon2=new Coupon();
                        coupon2.setCouponCode("XX150BB");
                        coupon2.setDiscountedPrice(15);
                        coupon2.setTotalOrderPrice(orderPrice);
                        couponList.add(coupon2);
                }
                else if (i==3){
                    Coupon coupon3=new Coupon();
                        coupon3.setCouponCode("CD100EF");
                        coupon3.setDiscountedPrice(10);
                    coupon3.setTotalOrderPrice(orderPrice);
                    couponList.add(coupon3);
                }
            }
            couponDao.saveAll(couponList);
        }
        else if(orderPrice>10001 && orderPrice<25000){
            ArrayList<Coupon> couponList2 = new ArrayList<>();
            for(int i=1 ; i<=3;i++) {
                System.out.println("i"+i);
                switch (i) {
                    case 1:
                        Coupon coupon1=new Coupon();
                        coupon1.setCouponCode("CD400EF");
                        coupon1.setDiscountedPrice(20);
                        coupon1.setTotalOrderPrice(orderPrice);
                        couponList2.add(coupon1);

                        break;
                    case 2:
                        Coupon coupon2=new Coupon();
                        coupon2.setCouponCode("GH450IJ");
                        coupon2.setDiscountedPrice(25);
                        coupon2.setTotalOrderPrice(orderPrice);
                        couponList2.add(coupon2);

                        break;
                    case 3:
                        Coupon coupon3=new Coupon();
                        coupon3.setCouponCode("KL600MN");
                        coupon3.setDiscountedPrice(30);
                        coupon3.setTotalOrderPrice(orderPrice);
                        couponList2.add(coupon3);
                        break;
                }
            }
            couponDao.saveAll(couponList2);
        }
        else if(orderPrice>=25000){
            ArrayList<Coupon> couponList3 = new ArrayList<>();
            for(int i=1 ; i<=3;i++) {
                switch (i) {
                    case 1:
                        Coupon coupon1=new Coupon();
                        coupon1.setCouponCode("BUM600ER");
                        coupon1.setDiscountedPrice(30);
                        coupon1.setTotalOrderPrice(orderPrice);
                        couponList3.add(coupon1);
                        break;
                    case 2:
                        Coupon coupon2=new Coupon();
                        coupon2.setCouponCode("MU650IJ");
                        coupon2.setDiscountedPrice(35);
                        coupon2.setTotalOrderPrice(orderPrice);
                        couponList3.add(coupon2);
                        break;
                    case 3:
                        Coupon coupon3=new Coupon();
                        coupon3.setCouponCode("KL700MN");
                        coupon3.setDiscountedPrice(40);
                        coupon3.setTotalOrderPrice(orderPrice);
                        couponList3.add(coupon3);
                        break;
                }
            }
            couponDao.saveAll(couponList3);
        }
        return "Coupons are created";

    }

    //update Coupon method
   /* public Coupon updateCoupon(Coupon coupon, Integer couponId) {
        Coupon coupon1 = this.couponDao.findById(couponId).orElseThrow(() -> new ResourceNotFoundException("Coupon not found with this id " + couponId));
        coupon1.setCouponCode(coupon.getCouponCode());
        coupon1.setDiscountedPrice(coupon.getDiscountedPrice());
        coupon1.setExpiryDate(new Date());
        coupon1.setSubCatId(coupon.getSubCatId());
        Coupon updateCoupon = this.couponDao.save(coupon);
        return updateCoupon;
    }*/

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

    // Get coupon by orderPrice
      public List<Coupon> getCoponByPrice(double orderPrice){
        List<Coupon> coupons=new ArrayList<>();

        if(orderPrice>5000&& orderPrice<=10000)
          {   double totalPrice=7000;
              coupons = couponDao.findByTotalOrderPrice(totalPrice);
              return  coupons;
          }
        else if(orderPrice>10001 && orderPrice<25000)
        {
            double totalPrice=15000;
           coupons = couponDao.findByTotalOrderPrice(totalPrice);
           return  coupons;
        }
        else if(orderPrice>=25000){
            double totalPrice=30050;
            coupons = couponDao.findByTotalOrderPrice(totalPrice);
            return  coupons;

        }
          return null;
    }


  /*  public Coupon getSubcategory(Integer productId){
        Product product = productDao.findById(productId).orElseThrow(()-> new ResourceNotFoundException("User not found with this id"+productId));
        SubCategory subCategory =product.getSubCategory();
        int id = subCategory.getSubCatId();
        return couponDao.findBySubCatId(id);
    }*/

   public double applyCoupon(String couponCode, double orderPrice) {
       Coupon coupon = couponDao.findByCouponCode(couponCode);
       if (couponCode == null) {
           System.out.println("entered into null");
           return 0.0;
       } else if (couponCode.equals("BUM600ER")) {
           double dicountnew=coupon.getDiscountedPrice();
           double percent=dicountnew/100;
           double discountedPrice = orderPrice*(1-percent);
           return discountedPrice;
       } else if (couponCode.equals("MU650IJ")) {
           double dicountnew=coupon.getDiscountedPrice();
           double percent=dicountnew/100;
           double discountedPrice = orderPrice*(1-percent);
           return discountedPrice;
       }
       else if (couponCode.equals("KL700MN")) {
        double dicountnew=coupon.getDiscountedPrice();
        double percent=dicountnew/100;
        double discountedPrice = orderPrice*(1-percent);
        return discountedPrice;
    }
       else if (couponCode.equals("XY200AB")) {
           double dicountnew=coupon.getDiscountedPrice();
           double percent=dicountnew/100;
           double discountedPrice = orderPrice*(1-percent);
           return discountedPrice;
       }
       else if (couponCode.equals("XX150BB")) {
           double dicountnew=coupon.getDiscountedPrice();
           double percent=dicountnew/100;
           double discountedPrice = orderPrice*(1-percent);
           return discountedPrice;
       }
       else if (couponCode.equals("CD100EF")) {
           double dicountnew=coupon.getDiscountedPrice();
           double percent=dicountnew/100;
           double discountedPrice = orderPrice*(1-percent);
           return discountedPrice;
       }
       else if (couponCode.equals("CD400EF")) {
           double dicountnew=coupon.getDiscountedPrice();
           double percent=dicountnew/100;
           double discountedPrice = orderPrice*(1-percent);
           return discountedPrice;
       }
       else if (couponCode.equals("GH450IJ")) {
           double dicountnew=coupon.getDiscountedPrice();
           double percent=dicountnew/100;
           double discountedPrice = orderPrice*(1-percent);
           return discountedPrice;
       }
       else if (couponCode.equals("KL600MN")) {
           double dicountnew=coupon.getDiscountedPrice();
           double percent=dicountnew/100;
           double discountedPrice = orderPrice*(1-percent);
           return discountedPrice;
       }
       return orderPrice;
   }
}

