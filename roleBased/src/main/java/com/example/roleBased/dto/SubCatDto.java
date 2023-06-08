package com.example.roleBased.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCatDto {

    private int subCatId;
    private String subCatName;
    private CategoryDto category;

}
