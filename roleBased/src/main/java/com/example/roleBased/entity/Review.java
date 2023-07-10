package com.example.roleBased.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String review;

    private String reviewerName;

    private int rating;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product" , nullable = false)
    private Product product;

    public void setProduct(Product product) { this.product = product; }
}
