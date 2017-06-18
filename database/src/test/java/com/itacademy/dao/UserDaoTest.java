package com.itacademy.dao;

import com.itacademy.entity.*;
import com.itacademy.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.junit.*;

import java.util.*;

import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class UserDaoTest {

    private SessionFactory sessionFactory;
    UserDao userDao = new UserDao();


    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void saveOneUser() {
        User user = new User("testName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        assertEquals(user.getName(), "testName");
        userDao.deleteOneById(1L);
    }


    @Test
    public void testGetUserById() {
        User user = new User("testName", "testName@gmail.com",
                "test", "user");
        userDao.saveOne(user);
        User user1 = userDao.findOneById(1L);
        assertThat(user1, notNullValue());
        System.out.println(user1);
        userDao.deleteOneById(1L);
    }


    @Test
    public void updateOneById() {
        User user = new User("testName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        User user1 = userDao.findOneById(1L);
        user1.setName("anotherName");
        userDao.updateOneById(1L);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(user);
        assertEquals(user1.getName(), "anotherName");
        userDao.deleteOneById(1L);
    }

    @Test
    public void deleteOneById() {
        User user = new User("testName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        userDao.deleteOneById(1L);
        User user1 = userDao.findOneById(1L);
        assertThat(user1, nullValue());
    }

    @Test
    public void testFindAll() {
        User user = new User("oneName", "testName@gmail.com", "test", "user");
        User user2 = new User("anotherName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        userDao.saveOne(user2);
        List<User> results = userDao.findAll();
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(results);
    }


    @After
    public void finish() {
        sessionFactory.close();
    }
}

