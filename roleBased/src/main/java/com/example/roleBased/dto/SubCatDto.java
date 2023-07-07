package com.example.roleBased.dto;

import com.example.roleBased.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class SubCatDto {

    private int subCatId;
    private String subCatName;
    private CategoryDto category;

    public SubCatDto(int i, String skirts, Category category) {
    }

    public SubCatDto(int subCatId, String subCatName, CategoryDto category) {
        this.subCatId = subCatId;
        this.subCatName = subCatName;
        this.category = category;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
