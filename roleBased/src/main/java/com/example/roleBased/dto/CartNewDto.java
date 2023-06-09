package com.example.roleBased.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@NoArgsConstructor
public class CartNewDto {

   private int cartNewId;
    private int quantity=1;
    private double totalPrice;
    private ProductDto product;
    private String userid;


    public CartNewDto(int cartNewId, int quantity, double totalPrice, ProductDto product, String userid) {
        this.cartNewId = cartNewId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
        this.userid = userid;

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

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
