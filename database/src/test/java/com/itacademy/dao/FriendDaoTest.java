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


/**
 * Created by Tom on 13.06.2017.
 */
public class FriendDaoTest {

    private SessionFactory sessionFactory;
    UserDao userDao = new UserDao();
    FriendDao friendDao = new FriendDao();



    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void testSaveOne() {
        User sender = new User("testName", "testName@gmail.com", "test", "user");
        User reciver = new User("anothertestName", "testName@gmail.com", "test", "user");
        userDao.saveOne(sender);
        userDao.saveOne(reciver);
        Friend friend = new Friend("fri", sender, reciver);
        friendDao.saveOne(friend);
        Friend friend1 = friendDao.findOneById(1L);
        assertThat(friend1, notNullValue());
        System.out.println(friend1);
    }

    @Test
    public void testGetFriendById() {
        User sender = new User("testName", "testName@gmail.com", "test", "user");
        User reciver = new User("anothertestName", "testName@gmail.com", "test", "user");
        userDao.saveOne(sender);
        userDao.saveOne(reciver);
        Friend friend = new Friend("fri", sender, reciver);
        friendDao.saveOne(friend);
        Friend friend1 = friendDao.findOneById(1L);
        assertThat(friend1, notNullValue());
        System.out.println(friend1);
    }

    @Test
    public void testUpdateOneById() {
        User sender = new User("testName", "testName@gmail.com", "test", "user");
        User reciver = new User("anothertestName", "testName@gmail.com", "test", "user");
        userDao.saveOne(sender);
        userDao.saveOne(reciver);
        Friend friend = new Friend("req", sender, reciver);
        friendDao.saveOne(friend);
        Friend friend1 = friendDao.findOneById(1L);
        assertThat(friend1, notNullValue());
        friend1.setStatus("fri");
        friendDao.updateOneById(1L);
        assertEquals(friend1.getStatus(), "fri");
        friendDao.deleteOneById(1L);
    }

    @Test
    public void testDeleteOneById() {
        User sender = new User("testName", "testName@gmail.com", "test", "user");
        User reciver = new User("anothertestName", "testName@gmail.com", "test", "user");
        userDao.saveOne(sender);
        userDao.saveOne(reciver);
        Friend friend = new Friend("req", sender, reciver);
        friendDao.saveOne(friend);
        friendDao.deleteOneById(1L);
        Friend friend1 = friendDao.findOneById(1L);
        assertThat(friend1, nullValue());
    }

    @Test
    public void testFindAllFriendsByUserId() {
        User user = new User("senderName", "testName@gmail.com", "test", "user");
        User user2 = new User("friendOne", "testName@gmail.com", "test", "user");
        User user3 = new User("friendOne", "testName@gmail.com", "test", "user");
        userDao.saveOne(user);
        userDao.saveOne(user2);
        userDao.saveOne(user3);
        Friend friend1 = new Friend("fri", user, user2);
        Friend friend2 = new Friend("fri", user, user3);
        friendDao.saveOne(friend1);
        friendDao.saveOne(friend2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Friend> results = friendDao.findAllFriendsByUserId(session, "senderName", "fri");
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(results);
        session.getTransaction().commit();
        session.close();

    }

    @After
    public void finish() {
        sessionFactory.close();
    }
}
