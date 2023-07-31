package com.example.roleBased.dto;

import com.example.roleBased.dao.SubCategoryDao;
import com.example.roleBased.entity.SubCategory;
import lombok.*;

import javax.persistence.ManyToOne;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {

    private int couponId;
    private int discountedPrice;
    private String couponCode;


}
