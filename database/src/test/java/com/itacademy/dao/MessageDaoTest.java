package com.itacademy.dao;

import com.itacademy.entity.Message;
import com.itacademy.entity.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class MessageDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;

    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveMessage() {
        SystemUser user1 = new SystemUser();
        SystemUser user2 = new SystemUser();
        userDao.save(user1);
        userDao.save(user2);
        Message message = new Message();
        message.setCreationDate(LocalDateTime.now());
        message.setText("Hello!");
        message.setUserReceiver(user1);
        message.setUserSender(user2);
        Long messageId = messageDao.save(message);
        Message message1 = messageDao.findById(messageId);
        assertEquals(message1.getText(), "Hello!");
        assertThat(message1.getCreationDate(), notNullValue());

    }

    @Test
    public void testGetMessageById() {
        Message message = new Message();
        Long messageId = messageDao.save(message);
        Message message1 = messageDao.findById(messageId);
        assertThat(message1, notNullValue());
        System.out.println(message1);
    }

    @Test
    public void testDeleteMessage() {
        SystemUser user1 = new SystemUser();
        SystemUser user2 = new SystemUser();
        userDao.save(user1);
        userDao.save(user2);
        Message message = new Message();
        message.setText("Hello!");
        message.setUserReceiver(user1);
        message.setUserSender(user2);
        Long messageId = messageDao.save(message);
        messageDao.delete(message);
        Message message1 = messageDao.findById(messageId);
        assertThat(message1, nullValue());
    }


    @After
    public void finish() {
        // ...
    }
}