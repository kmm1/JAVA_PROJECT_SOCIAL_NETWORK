package com.itacademy.dao;

import com.itacademy.entity.*;
import com.itacademy.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class BlogDaoTest {

    private SessionFactory sessionFactory;
    private UserDao userDao = new UserDao();
    private BlogDao blogDao = new BlogDao();


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

    @After
    public void finish() {
        sessionFactory.close();
    }
}

