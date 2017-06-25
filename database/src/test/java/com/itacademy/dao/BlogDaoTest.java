package com.itacademy.dao;

import com.itacademy.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BlogDaoTest extends BaseTest {


    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BlogDao blogDao;


    @Before
    public void init() {
        // ....
    }


    @Test
    public void testSaveBlog() {
        User blogger = new User();
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
        User user = new User();
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


    @After
    public void finish() {
        // ...
    }
}

