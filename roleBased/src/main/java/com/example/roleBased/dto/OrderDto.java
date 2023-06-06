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

    private UserDto user;

}
