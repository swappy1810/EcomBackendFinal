package com.example.roleBased.controller;

import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.dto.SubCatDto;
import com.example.roleBased.exception.ApiResponse;
import com.example.roleBased.serviceImpl.CategoryServiceImpl;
import com.example.roleBased.serviceImpl.SubCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("subCat")
public class SubCatController {

    @Autowired
    private SubCategoryServiceImpl subCategoryService;

    //add product to products
    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/save/{catId}")
    public ResponseEntity<SubCatDto> createSubCategory(@Valid @RequestBody SubCatDto subCatDto,@PathVariable Integer catId){
        SubCatDto createSubCategoryDto = this.subCategoryService.createSubCategory(subCatDto,catId);
        return new ResponseEntity<>(createSubCategoryDto, HttpStatus.CREATED);
    }

    //update product by product id
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<SubCatDto> updateSubCategory(@Valid @RequestBody SubCatDto subCatDto, @PathVariable Integer subCatId){
        SubCatDto updateSubCat = this.subCategoryService.updateSubCategory(subCatDto,subCatId);
        updateSubCat.setSubCatName(subCatDto.getSubCatName());
        return ResponseEntity.ok(updateSubCat);
    }

    //delete the product by product Id
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSubCategory(@PathVariable("subCatId") Integer subCatId){
        this.subCategoryService.deleteProduct(subCatId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("SubCategory Deleted Successfully",true), HttpStatus.OK);
    }

    //get all product
    @GetMapping("/")
    public ResponseEntity<List<SubCatDto>> getAllProducts(){
        return ResponseEntity.ok(this.subCategoryService.getAllProduct());
    }

    //get product by product id
    @GetMapping("/{id}")
    public ResponseEntity<SubCatDto> getSubCategoryById(@PathVariable("catId") Integer catId){
        return ResponseEntity.ok(this.subCategoryService.getSubCatById(catId));
    }


}
