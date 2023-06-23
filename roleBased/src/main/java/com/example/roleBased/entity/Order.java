package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(name = "date")
    private Date addedDate;
    private int quantity;
    private String status;
    private double totalPrice;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String mobileNo;
//    private int userCartId;

    @OneToOne
    private User user;
    //mapped order by product
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;



}