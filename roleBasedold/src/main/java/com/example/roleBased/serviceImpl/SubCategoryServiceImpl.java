package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CategoryDao;
import com.example.roleBased.dao.SubCategoryDao;
import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.dto.SubCatDto;
import com.example.roleBased.entity.Category;
import com.example.roleBased.entity.Order;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.SubCategory;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl {

    ModelMapper modelMapper= new ModelMapper();

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Autowired
    private CategoryDao categoryDao;

    //create category method
    public SubCatDto createSubCategory(SubCatDto subCatDto,Integer catId) {
        Category category = this.categoryDao.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category not found with this id"+catId));
        SubCategory subCategory= this.dtoToSubCat(subCatDto);
        subCategory.setCategory(category);
        subCategory.setSubCatName(subCatDto.getSubCatName());
        SubCategory save = this.subCategoryDao.save(subCategory);
        return this.subCatToDto(save);
    }
    //update category method
    public SubCatDto updateSubCategory(SubCatDto subCatDto, Integer subCatId) {
        SubCategory subCategory = this.subCategoryDao.findById(subCatId).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id " + subCatId));
        subCategory.setSubCatName(subCatDto.getSubCatName());
        SubCatDto subCatDto1 = this.subCatToDto(subCategory);
        return subCatDto1;
    }

    //get product by product Id method
    public SubCatDto getSubCatById(Integer subCatId) {
        SubCategory category = subCategoryDao.findById(subCatId).orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with this id " + subCatId));
        return this.subCatToDto(category);
    }

    //get all product method
    public List<SubCatDto> getAllProduct() {
        List<SubCategory> categories = this.subCategoryDao.findAll();
        return categories.stream().map(subCategory -> this.subCatToDto(subCategory)).collect(Collectors.toList());
    }

    //delete product by product id method
    public ResponseEntity<CategoryDto> deleteProduct(Integer subCatId) {
        SubCategory subCategory = subCategoryDao.findById(subCatId).orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with this id " + subCatId));
        this.subCategoryDao.deleteById(subCatId);
        return null;
    }

    //dto to subCat fetch
    private SubCategory dtoToSubCat(SubCatDto subCatDto) {
        SubCategory subCategory = modelMapper.map(subCatDto,SubCategory.class);
        return subCategory;
    }

    //subCat to dto fetch
    private SubCatDto subCatToDto(SubCategory subCategory) {
        SubCatDto subCatDto = modelMapper.map(subCategory,SubCatDto.class);
        return  subCatDto;
    }
}
