package com.example.roleBased.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private int quantity;
    private double price;
    private String status;
    private ProductDto product;
    private String addressLine1;
    private String addressLine2;
    private Date date;
}
