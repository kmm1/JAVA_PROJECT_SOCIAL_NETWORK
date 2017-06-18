package com.itacademy.dao;

import com.itacademy.entity.*;
import com.itacademy.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.junit.*;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class MessageDaoTest {

    private SessionFactory sessionFactory;
    UserDao userDao = new UserDao();
    MessageDao messageDao = new MessageDao();



    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        //  TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void testSaveMessage() {
        User user1 = new User("senderName", "testName@gmail.com", "test", "user");
        User user2 = new User("receiverName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user1);
        userDao.saveOne(user2);
        Message message = new Message();
        message.setCreationDate(LocalDateTime.now());
        message.setText("Hello!");
        message.setUserReceiver(user1);
        message.setUserSender(user2);
        messageDao.saveOne(message);
        Message message1 = messageDao.findOneById(1L);
        assertEquals(message1.getText(), "Hello!");
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(user1);
        System.out.println(message1);
        messageDao.deleteOneById(1L);
        userDao.deleteOneById(1L);
        userDao.deleteOneById(2L);
    }

    //TODO ne rabotaet
    @Test
    public void testGetMessageById() {
        Message message = new Message();
        messageDao.saveOne(message);
        Message message1 = messageDao.findOneById(1L);
        assertThat(message1, notNullValue());
        System.out.println(message1);
        messageDao.deleteOneById(1L);
    }

    @Test
    public void testDeleteMessage() {
        User user1 = new User("senderName", "testName@gmail.com", "test", "user");
        User user2 = new User("receiverName", "testName@gmail.com", "test", "user");
        userDao.saveOne(user1);
        userDao.saveOne(user2);
        Message message = new Message();
        message.setCreationDate(LocalDateTime.now());
        message.setText("Hello!");
        message.setUserReceiver(user1);
        message.setUserSender(user2);
        messageDao.saveOne(message);
        messageDao.deleteOneById(1L);
        Message message1 = messageDao.findOneById(1L);
        assertThat(message1, nullValue());
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(user1);
        System.out.println(message1);
        userDao.deleteOneById(1L);
        userDao.deleteOneById(2L);
    }


    @After
    public void finish() {
        sessionFactory.close();
    }

}

