package com.example.roleBased.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {

    private int id;
    private int quantity=1;
    private double totalPrice;
    private UserDto user;
    private int userCartId;
    private ProductDto product;

}