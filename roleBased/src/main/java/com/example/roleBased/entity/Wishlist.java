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

        private int userId;

        //created_date column
        @Column(name = "created_date")
        private Date createdDate;

        private int quantity=1;

        private String username;

    public int getListid() {
        return Listid;
    }

    public void setListid(int listid) {
        Listid = listid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

