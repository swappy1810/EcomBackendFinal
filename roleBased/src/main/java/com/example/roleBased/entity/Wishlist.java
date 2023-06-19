package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WishList")
public class Wishlist {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int Listid;

        //Object of product class to store the product information
        @ManyToOne
        @JoinColumn(name = "productid")
        private Product product;

        @OneToOne
        @JoinColumn(name = "userid")
        private User user;

        //created_date column
        @Column(name = "created_date")
        private Date createdDate;

        private int quantity;
        private String username;


    public String getUsername() {
        return username;
    }
}

