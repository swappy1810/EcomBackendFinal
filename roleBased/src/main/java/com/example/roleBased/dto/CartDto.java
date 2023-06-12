package com.example.roleBased.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private int userCartId;
    private double totalPrice;
    private CartDetailDto cartDetail;

}
