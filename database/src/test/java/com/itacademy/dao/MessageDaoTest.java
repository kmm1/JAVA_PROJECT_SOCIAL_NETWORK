package com.itacademy.dao;

import com.itacademy.entity.Message;
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


public class MessageDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;

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

    @Test
    public void testUpdateMessage() {
        Message message = new Message();
        message.setText("firstText");
        Long messageId = messageDao.save(message);
        assertEquals(message.getText(), "firstText");
        Message message1 = messageDao.findById(messageId);
        message1.setText("anotherText");
        messageDao.update(message1);
        assertEquals(message1.getText(), "anotherText");
    }

    @Test
    public void testFindAllMessages() {
        Message message1 = new Message();
        Message message2 = new Message();
        messageDao.save(message1);
        messageDao.save(message2);
        List<Message> results = messageDao.findAll();
        assertEquals(results.size(), 2);
    }

    @Test
    public void testChatByTwoUsers() {
        SystemUser user1 = new SystemUser();
        SystemUser user2 = new SystemUser();
        Long userId1 = userDao.save(user1);
        Long userId2 = userDao.save(user2);
        Message message = new Message();
        message.setText("Hello!");
        message.setUserReceiver(user1);
        message.setUserSender(user2);
        Long messageId = messageDao.save(message);
        List<Message> results = messageDao.chatByTwoUsers(userId1, userId2);
        assertThat(results, notNullValue());
    }

    @Test
    public void testFindMessagesByUserName() {
        SystemUser user1 = new SystemUser();
        SystemUser user2 = new SystemUser();
        user1.setName("firstName");
        user2.setName("secondName");
        Long userId1 = userDao.save(user1);
        Long userId2 = userDao.save(user2);
        Message message = new Message();
        message.setText("Hello!");
        message.setUserReceiver(user1);
        message.setUserSender(user2);
        Long messageId = messageDao.save(message);
        List<Message> results = messageDao.findMessagesByUserName(userId1);
        assertThat(results, notNullValue());
    }

    @Test
    public void testNames() {
        SystemUser user1 = new SystemUser();
        SystemUser user2 = new SystemUser();
        user1.setName("firstName");
        user2.setName("secondName");
        Long userId1 = userDao.save(user1);
        Long userId2 = userDao.save(user2);
        Message message = new Message();
        message.setText("Hello!");
        message.setUserReceiver(user1);
        message.setUserSender(user2);
        Long messageId = messageDao.save(message);
        List<String> result = messageDao.names(userId1, "firstName");
        assertThat(result, notNullValue());
    }

}