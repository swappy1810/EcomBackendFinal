package com.example.roleBased.dto;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private int cartId;
    private int quantity;
    private double totalPrice;

}
