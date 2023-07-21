package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tags")
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;
    @OneToOne
    private Product product;

    private double productPrice;

    private int discountPercent;

    private double totalPrice;

}
