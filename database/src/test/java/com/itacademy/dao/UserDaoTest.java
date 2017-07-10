package com.itacademy.dao;

import com.itacademy.entity.Role;
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

    @Autowired
    private RoleDao roleDao;


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
    public void testFindUserByName() {
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
    public void testDeleteUser() {
        SystemUser user = new SystemUser();
        Long userId = userDao.save(user);
        userDao.delete(user);
        SystemUser user1 = userDao.findById(userId);
        assertThat(user1, nullValue());
    }

    @Test
    public void testFindAllUsers() {
        SystemUser user1 = new SystemUser();
        SystemUser user2 = new SystemUser();
        userDao.save(user1);
        userDao.save(user2);
        List<SystemUser> results = userDao.findAll();
        assertEquals(results.size(), 2);
    }

    @Test
    public void testFindUserByNamePassword() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name");
        user1.setPassword("Password");
        Long userId = userDao.save(user1);
        List<SystemUser> result = userDao.findUserByNamePassword("Name", "Password");
        assertEquals(result.size(), 1);
    }

    @Test
    public void testFindOneUserByName() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name");
        userDao.save(user1);
        SystemUser result = userDao.findOneUserByName("Name");
        assertThat(result, notNullValue());
    }

    @Test
    public void testFindOneUserByName2() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name");
        userDao.save(user1);
        List<SystemUser> result = userDao.findOneUserByName2("Name");
        assertThat(result, notNullValue());
    }

    @Test
    public void testAddExistingRoleToExistingUser() {
        Role role = new Role();
        role.setName("ADMIN");
        Long roleId = roleDao.save(role);
        SystemUser user1 = new SystemUser();
        Long userId = userDao.save(user1);
        userDao.addExistingRoleToExistingUser(roleId, userId);
        SystemUser result = userDao.findById(userId);
        assertThat(result.getRoles(), notNullValue());
    }

}

