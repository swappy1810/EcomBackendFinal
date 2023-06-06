package com.example.roleBased.entity;

import com.example.roleBased.dto.ProductDto;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private int quantity;
    private double totalPrice;
    @OneToOne
    private Product product;
    @OneToOne
    private User user;


    public Cart(Product product, User user) {
        this.product=product;
        this.user=user;

    }
}
