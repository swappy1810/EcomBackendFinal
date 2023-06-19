package com.example.roleBased.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;
    private Date addedDate;
    private String status;
    private int quantity;
    private ProductDto product;
    private int userCartId;
    private double totalPrice;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String mobileNo;

}
