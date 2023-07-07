package com.example.roleBased.dto;

import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDto {

    @NotNull
    private ProductDto ProductId;

    private UserDto user;

    @NotBlank
    private String Comment;

    @NotBlank
    private int Rating;

    @NotBlank
    private byte image;


}
