package com.example.roleBased.Service;

import com.example.roleBased.dao.SubCategoryDao;
import com.example.roleBased.dto.SubCatDto;
import com.example.roleBased.entity.Category;
import com.example.roleBased.entity.SubCategory;
import com.example.roleBased.serviceImpl.SubCategoryServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.when;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubCategoryService {

    ModelMapper modelMapper= new ModelMapper();

    @Autowired
    private TestEntityManager testEntityManager;

    @MockBean
    private SubCategoryServiceImpl subCategoryService;

    @MockBean
    private SubCategoryDao repo;
    private SubCategory subCategory;

    @BeforeEach
    public void setup() {
        Category category=new Category(2,"Women");
        SubCategory subcategory= new SubCategory(1,"Skirts",category);
        System.out.println(subcategory);
    }
    @Parameter
    private SubCatDto subCatToDto(SubCategory subCategory) {
        SubCatDto subCatDto = modelMapper.map(subCategory,SubCatDto.class);
        return subCatDto;
    }


    @Test
    public void testSaveEmployee(){
        Category category=new Category(2,"Women");
        SubCategory subcategory= new SubCategory(1,"Skirts",category);
        repo.save(subcategory);
      //  subCategoryService.createSubCategory(subCategory,2);
        Assertions.assertThat(subcategory.getSubCatId()).isGreaterThan(0);


    }
}
