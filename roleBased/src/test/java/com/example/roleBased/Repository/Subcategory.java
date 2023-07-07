package com.example.roleBased.Repository;

import com.example.roleBased.dao.ProductDao;
import com.example.roleBased.dao.SubCategoryDao;
import com.example.roleBased.dto.ProductDto;
import com.example.roleBased.entity.Category;
import com.example.roleBased.entity.Product;
import com.example.roleBased.entity.SubCategory;
import com.example.roleBased.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.*;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Subcategory {

    @Autowired
    private TestEntityManager testEntityManager;

    @MockBean
    private SubCategoryDao repo;

    @Test
    public void createProduct() {
        Category category=new Category(2,"Women");
        SubCategory subcategory=new SubCategory(1,"Skirts",category);
        repo.save(subcategory);
        Assertions.assertThat(subcategory.getSubCatId()).isGreaterThan(0);
    }






}
