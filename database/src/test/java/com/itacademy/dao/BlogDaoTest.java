package com.itacademy.dao;

import com.itacademy.entity.*;
import com.itacademy.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BlogDaoTest {

    private SessionFactory sessionFactory;
    private UserDao userDao = new UserDao();
    private BlogDao blogDao = new BlogDao();
    private CategoryDao categoryDao = new CategoryDao();

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        // TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void testSaveOne() {
        User blogger = new User("testName", "testName@gmail.com", "test", "user");
        userDao.saveOne(blogger);
        Blog blog = new Blog();
        blog.setCreationDate(LocalDateTime.now());
        blog.setTitle("Title Content");
        blog.setText("Text Content");
        blog.setUser(blogger);
        blogDao.saveOne(blog);
        assertThat(blog, notNullValue());
        System.out.println(blog);
        blogDao.deleteOneById(1L);
        userDao.deleteOneById(1L);
    }

    @Test
    public void testGetBlogById() {
        Blog blog = new Blog();
        blog.setTitle("Title Content");
        blogDao.saveOne(blog);
        Blog blog1 = blogDao.findOneById(1L);
        assertThat(blog1, notNullValue());
        System.out.println(blog);
        blogDao.deleteOneById(1L);
    }

    @Test
    public void testUpdateOneById() {
        Blog blog = new Blog();
        blog.setTitle("Title");
        blogDao.saveOne(blog);
        Blog blog1 = blogDao.findOneById(1L);
        blog1.setTitle("Another");
        blogDao.updateOneById(1L);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(blog);
        assertEquals(blog1.getTitle(), "Another");
        blogDao.deleteOneById(1L);
    }

    @Test
    public void deleteOneById() {
        Blog blog = new Blog();
        blog.setTitle("Title Content");
        blogDao.saveOne(blog);
        blogDao.deleteOneById(1L);
        Blog blog1 = blogDao.findOneById(1L);
        assertThat(blog1, nullValue());
    }

    @Test
    public void testAddCategoryToBlog() {
        Blog blog = new Blog();
        blog.setTitle("Title Content");
        blogDao.saveOne(blog);
        blogDao.deleteOneById(1L);
        Blog blog1 = blogDao.findOneById(1L);
        assertThat(blog1, nullValue());
    }


    @Test
    public void testCreateBlog() {
        Blog blog = new Blog();
        User user = new User();
        Category category = new Category();
        Long userId = (Long) userDao.saveOne(user);
        Long categoryId = (Long) categoryDao.saveOne(category);
        categoryDao.saveOne(category);

        Long blogId = (Long) blogDao.createBlog(
                categoryId, userId, "test title", "test content");
        assertThat(blogId, notNullValue());
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(blog);
        System.out.println(blogId);
    }

    @Test
    public void testFindAll() {
        Blog blog1 = new Blog();
        Blog blog2 = new Blog();
        blogDao.saveOne(blog1);
        blogDao.saveOne(blog2);
        List<Blog> results = blogDao.findAll();
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(results);
    }

    @Test
    public void testAddExistingBlogToExistingCategory() {
        Blog blog = new Blog();
        User user = new User();
        Category category = new Category();
        Long blogId = (Long) blogDao.saveOne(blog);
        Long userId = (Long) userDao.saveOne(user);
        categoryDao.saveOne(category);
        blogDao.addExistingBlogToExistingCategory(1L, 1L, 1L);

    }


    @After
    public void finish() {
        sessionFactory.close();
    }
}

