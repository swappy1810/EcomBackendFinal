package com.example.roleBased.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private int userCartId;
    private double totalPrice;
    private CartDetailDto cartDetail;

    public int getUserCartId() {
        return userCartId;
    }

    public void setUserCartId(int userCartId) {
        this.userCartId = userCartId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDetailDto getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(CartDetailDto cartDetail) {
        this.cartDetail = cartDetail;
    }
}
