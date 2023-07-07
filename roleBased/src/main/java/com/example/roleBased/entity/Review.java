package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private int rating;

    @Column()
    private Byte image;

    public void setProduct(Product product){
        this.product = product;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setRating(int Rating){
        this.rating = rating;
    }

    public byte getId() {
        return image;
    }
}
