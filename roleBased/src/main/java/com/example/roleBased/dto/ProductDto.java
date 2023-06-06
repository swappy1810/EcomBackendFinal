package com.example.roleBased.dto;


import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

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
    private String product_desc;
    @NotNull
    private int product_price;

    private int quantity;

    private CategoryDto category;


}
