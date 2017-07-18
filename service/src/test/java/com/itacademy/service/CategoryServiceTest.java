package com.itacademy.service;

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

public class CategoryServiceTest extends BaseTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    @Test
    public void saveCategory() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        categoryService.save(category);
        assertEquals(category.getEnumCategory().toString(), "DIFFERENT");
    }

    @Test
    public void getCategoryById() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        Long categoryId = categoryService.save(category);
        Category category1 = categoryService.findById(categoryId);
        assertThat(category1, notNullValue());
        categoryService.delete(category1);
    }

    @Test
    public void testDeleteCategoryById() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        Long categoryId = categoryService.save(category);
        categoryService.delete(category);
        Category category1 = categoryService.findById(categoryId);
        assertThat(category1, nullValue());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        Long categoryId = categoryService.save(category);
        Category category2 = categoryService.findById(categoryId);
        category2.setEnumCategory(EnumCategory.CULTURE);
        categoryService.update(category2);
        System.out.println(category2);
        assertEquals(category2.getEnumCategory().toString(), "CULTURE");
    }

    @Test
    public void testFindAll() {
        Category category1 = new Category();
        Category category2 = new Category();
        categoryService.save(category1);
        categoryService.save(category2);
        List<Category> results = categoryService.findAll();
        assertEquals(results.size(), 2);
    }

    @Test
    public void testFindAllCategoriesByBlogId() {
        Category category1 = new Category();
        Category category2 = new Category();
        Long categoryId1 = categoryService.save(category1);
        Long categoryId2 = categoryService.save(category2);
        Blog blog = new Blog();
        Long blogId = blogService.save(blog);
        blogService.addExistingBlogToExistingCategory(categoryId1, blogId);
        blogService.addExistingBlogToExistingCategory(categoryId2, blogId);
        List<Category> resoults = categoryService.findAllCategoriesByBlogId(blogId);
        assertEquals(resoults.size(), 2);
    }

    @Test
    public void testFindOneCategoryByEnumCategory() {
        Category category1 = new Category();
        category1.setEnumCategory(EnumCategory.SCIENCE);
        Long categoryId1 = categoryService.save(category1);
        List<Category> resoults = categoryService.findOneCategoryByEnumCategory(EnumCategory.SCIENCE);
        assertEquals(resoults.size(), 1);
    }
}

