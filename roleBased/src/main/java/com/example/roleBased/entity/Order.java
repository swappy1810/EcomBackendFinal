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
    private String orderStatus;

    //mapped order by product
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    //mapped order by user
    @ManyToOne
    private User user;

}
