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
    private int uCartId;
    private double totalPrice;



}
