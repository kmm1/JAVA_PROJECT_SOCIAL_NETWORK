package com.itacademy.dao;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
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
    @Autowired
    private BlogDao blogDao;


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
    public void testDeleteCategoryById() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        Long categoryId = categoryDao.save(category);
        categoryDao.delete(category);
        Category category1 = categoryDao.findById(categoryId);
        assertThat(category1, nullValue());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        Long categoryId = categoryDao.save(category);
        Category category2 = categoryDao.findById(categoryId);
        category2.setEnumCategory(EnumCategory.CULTURE);
        categoryDao.update(category2);
        System.out.println(category2);
        assertEquals(category2.getEnumCategory().toString(), "CULTURE");
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

    @Test
    public void testFindAllCategoriesByBlogId() {
        Category category1 = new Category();
        Category category2 = new Category();
        Long categoryId1 = categoryDao.save(category1);
        Long categoryId2 = categoryDao.save(category2);
        Blog blog = new Blog();
        Long blogId = blogDao.save(blog);
        blogDao.addExistingBlogToExistingCategory(categoryId1, blogId);
        blogDao.addExistingBlogToExistingCategory(categoryId2, blogId);
        List<Category> resoults = categoryDao.findAllCategoriesByBlogId(blogId);
        assertEquals(resoults.size(), 2);
    }

    @Test
    public void testFindOneCategoryByEnumCategory() {
        Category category1 = new Category();
        category1.setEnumCategory(EnumCategory.SCIENCE);
        Long categoryId1 = categoryDao.save(category1);
        List<Category> resoults = categoryDao.findOneCategoryByEnumCategory(EnumCategory.SCIENCE);
        assertEquals(resoults.size(), 1);
    }
}

