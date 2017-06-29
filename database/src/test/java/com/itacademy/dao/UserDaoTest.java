package com.itacademy.dao;

import com.itacademy.entity.User;
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


public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("testName");
        user.setEmail("testName@gmail.com");
        user.setPassword("test");
        user.setRegistrationDate(LocalDateTime.now());
        user.setRole("user");
        Long userId = userDao.save(user);
        User user1 = userDao.findById(userId);
        assertEquals(user1.getName(), "testName");
        assertEquals(user1.getEmail(), "testName@gmail.com");
        assertEquals(user1.getPassword(), "test");
        assertThat(user1.getRegistrationDate(), notNullValue());
        assertEquals(user1.getRole(), "user");
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setName("name");
        Long userId = userDao.save(user);
        User user1 = userDao.findById(userId);
        assertThat(user1, notNullValue());
    }

    @Test
    public void testGetUserByName() {
        User user = new User();
        user.setName("name");
        Long userId = userDao.save(user);
        User user1 = userDao.findOneUserByName("name");
        assertThat(user1, notNullValue());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("firstName");
        Long userId = userDao.save(user);
        assertEquals(user.getName(), "firstName");
        User user1 = userDao.findById(userId);
        user1.setName("anotherName");
        userDao.update(user1);
        assertEquals(user1.getName(), "anotherName");
    }

    @Test
    public void deleteUser() {
        User user = new User();
        Long userId = userDao.save(user);
        userDao.delete(user);
        User user1 = userDao.findById(userId);
        assertThat(user1, nullValue());
    }

    @Test
    public void testFindAll() {
        User user1 = new User();
        User user2 = new User();
        userDao.save(user1);
        userDao.save(user2);
        List<User> results = userDao.findAll();
        assertEquals(results.size(), 2);
    }

    @After
    public void finish() {
        // ...
    }
}

