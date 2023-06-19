package com.example.roleBased.entity;

import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCartId;
    private double totalPrice;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = CartDetails.class)
    @JoinColumn(name = "cart_cartDetail")
    private List<CartDetails> cartDetails = new ArrayList<>();

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

    public List<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
