package com.example.roleBased.controller;

import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.serviceImpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    //add product to products
    @PostMapping("/save")
    public ResponseEntity<CategoryDto> createProduct(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategoryDto = this.categoryService.createProduct(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }

    //update product by product id
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateProduct(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        CategoryDto updatedCategory = this.categoryService.updateProduct(categoryDto,id);
        updatedCategory.setCatName(categoryDto.getCatName());
        return ResponseEntity.ok(updatedCategory);
    }

    //delete the product by product Id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id){
        this.categoryService.deleteProduct(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true), HttpStatus.OK);
    }

    //get all product
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllProducts(){
        return ResponseEntity.ok(this.categoryService.getAllProduct());
    }

    //get product by product id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getProductBYId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.categoryService.getProductById(id));
    }




}
