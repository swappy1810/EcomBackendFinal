package com.example.roleBased.serviceImpl;


import com.example.roleBased.dao.CategoryDao;
import com.example.roleBased.dto.CategoryDto;
import com.example.roleBased.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CategoryServiceTest {

    @MockBean
    private CategoryDao categoryDao;

    @Autowired
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup(){
        Optional<Category> category = Optional.of(new Category(1, "Men"));
        Mockito.when(categoryDao.findById(1)).thenReturn(category);
    }

   public void testGetCategoryById(){
        String catName = "Men";
        CategoryDto categoryById = categoryService.getProductById(1);
        assertThat(catName).isEqualTo(categoryById.getCatName());
   }

    public void testSaveCategory(){
        CategoryDto category = new CategoryDto(2,"Women");
        categoryService.createProduct(category);
        assertThat(category).isNotNull();
    }

    public void testGetCategories(){
        List<CategoryDto> category = categoryService.getAllProduct();
        assertThat(category).size().isGreaterThan(0);
    }
}
