package com.itacademy.dao;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
import com.itacademy.entity.SystemUser;
import org.h2.engine.User;
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


public class BlogDaoTest extends BaseTest {


    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BlogDao blogDao;


    @Test
    public void testSaveBlog() {
        SystemUser blogger = new SystemUser();
        userDao.save(blogger);
        Blog blog = new Blog();
        blog.setCreationDate(LocalDateTime.now());
        blog.setTitle("Title Content");
        blog.setText("Text Content");
        blog.setUser(blogger);
        blogDao.save(blog);
        assertEquals(blog.getTitle(), "Title Content");
        assertEquals(blog.getText(), "Text Content");
        assertEquals(blog.getUser(), blogger);
        assertThat(blog.getCreationDate(), notNullValue());
    }

    @Test
    public void testGetBlogById() {
        Blog blog = new Blog();
        Long blogId = blogDao.save(blog);
        Blog blog1 = blogDao.findById(blogId);
        assertThat(blog1, notNullValue());
    }

    @Test
    public void testUpdateBlogById() {
        Blog blog = new Blog();
        blog.setTitle("Title");
        Long blogId = blogDao.save(blog);
        Blog blog1 = blogDao.findById(blogId);
        blog1.setTitle("Another");
        blogDao.update(blog);
        assertEquals(blog1.getTitle(), "Another");
    }

    @Test
    public void deleteBlog() {
        Blog blog = new Blog();
        Long blogId = blogDao.save(blog);
        assertThat(blog, notNullValue());
        blogDao.delete(blog);
        Blog blog1 = blogDao.findById(blogId);
        assertThat(blog1, nullValue());
    }

    @Test
    public void testAddExistingBlogToExistingCategory() {
        Blog blog = new Blog();
        Long blogId = blogDao.save(blog);
        Category category = new Category();
        Long categoryId = categoryDao.save(category);
        blogDao.addExistingBlogToExistingCategory(categoryId, blogId);
        assertThat(blog.getCategories(), notNullValue());
    }


    @Test
    public void testCreateBlog() {
        Blog blog = new Blog();
        SystemUser user = new SystemUser();
        Category category = new Category();
        Long userId = userDao.save(user);
        Long categoryId = categoryDao.save(category);
        Long blogId = blogDao.createBlog(
                categoryId, userId, "test title", "test content");
        assertThat(blogId, notNullValue());
    }


    @Test
    public void testFindAllBlogs() {
        Blog blog1 = new Blog();
        Blog blog2 = new Blog();
        blogDao.save(blog1);
        blogDao.save(blog2);
        List<Blog> results = blogDao.findAll();
        assertEquals(results.size(), 2);
    }


    @Test
    public void testFindAllUsersBlogs() {
        SystemUser user = new SystemUser();
        Long userId = userDao.save(user);
        Blog blog1 = new Blog();
        Blog blog2 = new Blog();
        blog1.setUser(user);
        blog2.setUser(user);
        blogDao.save(blog1);
        blogDao.save(blog2);
        List<Blog> results = blogDao.findAllUsersBlogs(1L, 2, 0);
        assertEquals(results.size(), 2);
    }

    @Test
    public void testFindAllBlogsByCategory() {
        Blog blog = new Blog();
        Long blogId = blogDao.save(blog);
        Category category = new Category();
        category.setEnumCategory(EnumCategory.BUSINESS);
        Long categoryId = categoryDao.save(category);
        blogDao.addExistingBlogToExistingCategory(categoryId, blogId);
        List<Blog> results = blogDao.findAllBlogsByCategory(categoryId);
        assertEquals(results.size(), 1);
    }

    @Test
    public void testDeliteExistingBlogFromExistingCategory() {
        Blog blog = new Blog();
        Long blogId = blogDao.save(blog);
        Category category = new Category();
        category.setEnumCategory(EnumCategory.BUSINESS);
        Long categoryId = categoryDao.save(category);
        blogDao.addExistingBlogToExistingCategory(categoryId, blogId);
        List<Blog> results = blogDao.findAllBlogsByCategory(categoryId);
        assertEquals(results.size(), 1);
        blogDao.deliteExistingBlogFromExistingCategory(categoryId, blogId);
        List<Blog> results2 = blogDao.findAllBlogsByCategory(categoryId);
        assertEquals(results2.size(), 0);

    }

    @Test
    public void testCountUserBlogs() {
        SystemUser user = new SystemUser();
        Long userId = userDao.save(user);
        Blog blog1 = new Blog();
        Blog blog2 = new Blog();
        blog1.setUser(user);
        blog2.setUser(user);
        blogDao.save(blog1);
        blogDao.save(blog2);
        Integer resoult = blogDao.countUserBlogs(userId);
        assertThat(resoult, notNullValue());
    }

}

