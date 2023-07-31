package com.example.roleBased.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "subCategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCatId;
    private String subCatName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catId")
    private Category category;
    public SubCategory(){}
    public SubCategory(int subCatId, String subCatName, Category category) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
