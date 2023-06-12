package com.example.roleBased.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cartsNewfinal")
public class CartNew {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartNewId;

    private int quantity;
    private double totalPrice;
    @OneToOne
    private Product product;

    private String userid; //userid

    public CartNew(int cartNewId, int quantity, double totalPrice, Product product, String userid) {
        this.cartNewId = cartNewId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
        this.userid = userid;

    }

    public CartNew() {

    }

    public int getCartNewId() {
        return cartNewId;
    }

    public void setCartNewId(int cartNewId) {
        this.cartNewId = cartNewId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }




}
