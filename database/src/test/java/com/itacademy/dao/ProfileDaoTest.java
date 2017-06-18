package com.itacademy.dao;

import com.itacademy.entity.*;
import com.itacademy.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.junit.*;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class ProfileDaoTest {

    private SessionFactory sessionFactory;
    ProfileDao profileDao = new ProfileDao();
    UserDao userDao = new UserDao();



    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void saveProfileToUser() {
        User user = new User("testName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        profile.setHomeAddress(new Address("Belarus", "Minsk"));
        profile.setWorkAddress(new Address("Belarus", "Minsk"));
        profile.setMaritalStatus(EnumMaritalStatus.SINGLE);
        profile.setBirthday(new Birthday(1976, 07, 12));
        profile.setUser(user);
        profileDao.saveOne(profile);
        assertEquals(profile.getGender().toString(), "MALE");
        profileDao.deleteOneById(1L);
        userDao.deleteOneById(1L);
    }


    @Test
    public void testGetProfileById() {
        User user = new User("testName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        profile.setUser(user);
        profileDao.saveOne(profile);
        Profile profile1 = profileDao.findOneById(1L);
        assertThat(profile1, notNullValue());
        profileDao.deleteOneById(1L);
        userDao.deleteOneById(1L);
    }


    @Test
    public void updateOneById() {
        User user = new User("testName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        profile.setUser(user);
        profileDao.saveOne(profile);
        assertEquals(profile.getGender().toString(), "MALE");
        profile.setGender(EnumGender.FEMALE);
        profileDao.updateOneById(1L);
        assertEquals(profile.getGender().toString(), "FEMALE");
        profileDao.deleteOneById(1L);
        userDao.deleteOneById(1L);
    }

    @Test
    public void deleteOneById() {
        User user = new User("testName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        profile.setUser(user);
        profileDao.saveOne(profile);
        profileDao.deleteOneById(1L);
        Profile profile1 = profileDao.findOneById(1L);
        assertThat(profile1, nullValue());
        userDao.deleteOneById(1L);
    }

    @After
    public void finish() {
        sessionFactory.close();
    }

}

