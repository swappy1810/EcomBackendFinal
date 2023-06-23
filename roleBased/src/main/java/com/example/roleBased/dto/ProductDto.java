package com.example.roleBased.dto;


import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    private int product_id;
    @NotNull
    private String product_image;
    @NotBlank
    private String product_name;
    @NotBlank
    private String product_short_desc;
    @NotBlank
    private String product_long_desc;
    @NotNull
    private int product_price;

    private int quantity;

    private int rating;

    private CategoryDto category;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
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
}
