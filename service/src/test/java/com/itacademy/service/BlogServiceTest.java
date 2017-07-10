package com.itacademy.service;

import com.itacademy.dao.BlogDao;
import com.itacademy.dao.CategoryDao;
import com.itacademy.dao.UserDao;
import com.itacademy.entity.Blog;
import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
import com.itacademy.entity.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class BlogServiceTest extends BaseTest {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;


    @Test
    public void testSaveBlog() {
        SystemUser blogger = new SystemUser();
        userService.save(blogger);
        Blog blog = new Blog();
        blog.setCreationDate(LocalDateTime.now());
        blog.setTitle("Title Content");
        blog.setText("Text Content");
        blog.setUser(blogger);
        blogService.save(blog);
        assertEquals(blog.getTitle(), "Title Content");
        assertEquals(blog.getText(), "Text Content");
        assertEquals(blog.getUser(), blogger);
        assertThat(blog.getCreationDate(), notNullValue());
    }

    @Test
    public void testGetBlogById() {
        Blog blog = new Blog();
        Long blogId = blogService.save(blog);
        Blog blog1 = blogService.findById(blogId);
        assertThat(blog1, notNullValue());
    }

    @Test
    public void testUpdateBlogById() {
        Blog blog = new Blog();
        blog.setTitle("Title");
        Long blogId = blogService.save(blog);
        Blog blog1 = blogService.findById(blogId);
        blog1.setTitle("Another");
        blogService.update(blog);
        assertEquals(blog1.getTitle(), "Another");
    }

    @Test
    public void deleteBlog() {
        Blog blog = new Blog();
        Long blogId = blogService.save(blog);
        assertThat(blog, notNullValue());
        blogService.delete(blog);
        Blog blog1 = blogService.findById(blogId);
        assertThat(blog1, nullValue());
    }

    @Test
    public void testAddExistingBlogToExistingCategory() {
        Blog blog = new Blog();
        Long blogId = blogService.save(blog);
        Category category = new Category();
        Long categoryId = categoryService.save(category);
        blogService.addExistingBlogToExistingCategory(categoryId, blogId);
        assertThat(blog.getCategories(), notNullValue());
    }

    @Test
    public void testFindAllUsersBlogs() {
        SystemUser user = new SystemUser();
        Long userId = userService.save(user);
        Blog blog1 = new Blog();
        Blog blog2 = new Blog();
        blog1.setUser(user);
        blog2.setUser(user);
        blogService.save(blog1);
        blogService.save(blog2);
        List<Blog> results = blogService.findAllUsersBlogs(1L, 2, 0);
        assertEquals(results.size(), 2);
    }

    @Test
    public void testFindAllBlogsByCategory() {
        Blog blog = new Blog();
        Long blogId = blogService.save(blog);
        Category category = new Category();
        category.setEnumCategory(EnumCategory.BUSINESS);
        Long categoryId = categoryService.save(category);
        blogService.addExistingBlogToExistingCategory(categoryId, blogId);
        List<Blog> results = blogService.findAllBlogsByCategory(categoryId);
        assertEquals(results.size(), 1);
    }

    @Test
    public void testDeliteExistingBlogFromExistingCategory() {
        Blog blog = new Blog();
        Long blogId = blogService.save(blog);
        Category category = new Category();
        category.setEnumCategory(EnumCategory.BUSINESS);
        Long categoryId = categoryService.save(category);
        blogService.addExistingBlogToExistingCategory(categoryId, blogId);
        List<Blog> results = blogService.findAllBlogsByCategory(categoryId);
        assertEquals(results.size(), 1);
        blogService.deliteExistingBlogFromExistingCategory(categoryId, blogId);
        List<Blog> results2 = blogService.findAllBlogsByCategory(categoryId);
        assertEquals(results2.size(), 0);
    }

    @Test
    public void testCountUserBlogs() {
        SystemUser user = new SystemUser();
        Long userId = userService.save(user);
        Blog blog1 = new Blog();
        Blog blog2 = new Blog();
        blog1.setUser(user);
        blog2.setUser(user);
        blogService.save(blog1);
        blogService.save(blog2);
        Integer resoult = blogService.countUserBlogs(userId);
        assertThat(resoult, notNullValue());
    }

}

