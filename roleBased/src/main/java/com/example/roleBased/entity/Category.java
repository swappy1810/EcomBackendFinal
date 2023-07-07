package com.example.roleBased.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catId;
    @Column(name = "categoryName",nullable = false,unique = true)
    private String catName;



    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Category(){}
    public Category(int catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }
}
