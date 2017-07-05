package com.itacademy.dao;

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


public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveUser() {
        SystemUser user = new SystemUser();
        user.setName("testName");
        user.setEmail("testName@gmail.com");
        user.setPassword("test");
        user.setRegistrationDate(LocalDateTime.now());
        Long userId = userDao.save(user);
        SystemUser user1 = userDao.findById(userId);
        assertEquals(user1.getName(), "testName");
        assertEquals(user1.getEmail(), "testName@gmail.com");
        assertEquals(user1.getPassword(), "test");
        assertThat(user1.getRegistrationDate(), notNullValue());
    }

    @Test
    public void testGetUserById() {
        SystemUser user = new SystemUser();
        user.setName("name");
        Long userId = userDao.save(user);
        SystemUser user1 = userDao.findById(userId);
        assertThat(user1, notNullValue());
    }

    @Test
    public void testGetUserByName() {
        SystemUser user = new SystemUser();
        user.setName("name");
        Long userId = userDao.save(user);
        SystemUser user1 = userDao.findOneUserByName("name");
        assertThat(user1, notNullValue());
    }

    @Test
    public void testUpdateUser() {
        SystemUser user = new SystemUser();
        user.setName("firstName");
        Long userId = userDao.save(user);
        assertEquals(user.getName(), "firstName");
        SystemUser user1 = userDao.findById(userId);
        user1.setName("anotherName");
        userDao.update(user1);
        assertEquals(user1.getName(), "anotherName");
    }

    @Test
    public void deleteUser() {
        SystemUser user = new SystemUser();
        Long userId = userDao.save(user);
        userDao.delete(user);
        SystemUser user1 = userDao.findById(userId);
        assertThat(user1, nullValue());
    }

    @Test
    public void testFindAll() {
        SystemUser user1 = new SystemUser();
        SystemUser user2 = new SystemUser();
        userDao.save(user1);
        userDao.save(user2);
        List<SystemUser> results = userDao.findAll();
        assertEquals(results.size(), 2);
    }

    @After
    public void finish() {
        // ...
    }
}

