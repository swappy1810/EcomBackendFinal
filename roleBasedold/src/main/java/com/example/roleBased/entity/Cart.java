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

}
