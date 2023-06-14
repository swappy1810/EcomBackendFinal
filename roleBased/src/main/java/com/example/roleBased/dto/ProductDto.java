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


}
