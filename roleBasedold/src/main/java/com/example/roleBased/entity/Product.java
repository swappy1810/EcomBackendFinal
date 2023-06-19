package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    @Lob
    private String product_image;
    @Column(unique = true,nullable = false)
    private String product_name;
    @Column(nullable = false)
    private String product_short_desc;
    @Column(nullable = false)
    private String product_long_desc;
    @Column(nullable = false)
    private int product_price;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int rating;
    //mapped product with category
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catId",nullable = false)
    private Category category;



}
