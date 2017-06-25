package com.itacademy.dao;

import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CategoryDaoTest extends BaseTest {


    @Autowired
    private CategoryDao categoryDao;


    @Before
    public void init() {
        // ....
    }

    @Test
    public void saveCategory() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        categoryDao.save(category);
        assertEquals(category.getEnumCategory().toString(), "DIFFERENT");
    }

    @Test
    public void getCategoryById() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        Long categoryId = categoryDao.save(category);
        Category category1 = categoryDao.findById(categoryId);
        assertThat(category1, notNullValue());
        categoryDao.delete(category1);
    }

    @Test
    public void deleteCategoryById() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        Long categoryId = categoryDao.save(category);
        categoryDao.delete(category);
        Category category1 = categoryDao.findById(categoryId);
        assertThat(category1, nullValue());
    }

    @Test
    public void testFindAll() {
        Category category1 = new Category();
        Category category2 = new Category();
        categoryDao.save(category1);
        categoryDao.save(category2);
        List<Category> results = categoryDao.findAll();
        assertEquals(results.size(), 2);
    }


    @After
    public void finish() {
        // ...
    }
}

