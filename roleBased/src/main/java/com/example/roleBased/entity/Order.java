package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private int id;
    private double price;
    private int userId;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = OrderItems.class)
    @JoinColumn(name = "order_orderItems")
    private List<OrderItems> orderItems = new ArrayList<>();

}
