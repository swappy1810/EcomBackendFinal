package com.example.roleBased.dao;

import com.example.roleBased.entity.Category;
import com.example.roleBased.entity.SubCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubcategoryDao {

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
