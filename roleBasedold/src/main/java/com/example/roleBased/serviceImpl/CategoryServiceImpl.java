package com.example.roleBased.serviceImpl;

import com.example.roleBased.dao.CategoryDao;
import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.entity.Category;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryDao categoryDao;


    ModelMapper modelMapper = new ModelMapper();

    //create category method
    public CategoryDto createProduct(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category savedProduct = this.categoryDao.save(category);
        return this.categoryToDto(savedProduct);
    }
//update category method
    public CategoryDto updateProduct(CategoryDto categoryDto, Integer catId) {
        Category category = this.categoryDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found with this id " + catId));
        category.setCatName(categoryDto.getCatName());
        Category updateCategory = this.categoryDao.save(category);
        CategoryDto productDto1 = this.categoryToDto(updateCategory);
        return productDto1;
    }

    //get product by product Id method
    public CategoryDto getProductById(Integer catId) {
        Category category = categoryDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found with this id " + catId));
        return this.categoryToDto(category);
    }

    //get all product method
    public List<CategoryDto> getAllProduct() {
        List<Category> categories = this.categoryDao.findAll();
        List<CategoryDto> productDtos = categories.stream().map(product -> this.categoryToDto(product)).collect(Collectors.toList());
        return productDtos;
    }

    //delete product by product id method
    public ResponseEntity<CategoryDto> deleteProduct(Integer catId) {
        Category category = categoryDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found with this id " + catId));
        this.categoryDao.deleteById(catId);
        return null;
    }

    //dto to category method
    private Category dtoToCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        return category;
    }

    //category to dto method
    private CategoryDto categoryToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category,CategoryDto.class);
        return  categoryDto;
    }
}
