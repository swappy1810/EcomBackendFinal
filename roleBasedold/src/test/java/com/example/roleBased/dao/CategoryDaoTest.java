package com.example.roleBased.dao;

import com.example.roleBased.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

        @MockBean
        private CategoryDao categoryDao;

    @Test
    public void it_should_save_category() {
        Category category = new Category(1,"Men");
        categoryDao.save(category);
        assertThat(category.getCatId()).isGreaterThan(0);

    }

    }



