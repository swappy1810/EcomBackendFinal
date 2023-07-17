package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    @Lob
    private String product_image;
    @Column(unique = true,nullable = false)
    private String product_name;
    @Column(nullable = false)
    private String product_short_desc;
    @Column(nullable = false)
    private String product_long_desc;
    @Column(nullable = false)
    private int product_price;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private float rating;
    @Column(nullable = false)
    private int discount;
    //mapped product with category
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catId",nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subCatId",nullable = false)
    private SubCategory subCategory;

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_short_desc() {
        return product_short_desc;
    }

    public void setProduct_short_desc(String product_short_desc) {
        this.product_short_desc = product_short_desc;
    }

    public String getProduct_long_desc() {
        return product_long_desc;
    }

    public void setProduct_long_desc(String product_long_desc) {
        this.product_long_desc = product_long_desc;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
