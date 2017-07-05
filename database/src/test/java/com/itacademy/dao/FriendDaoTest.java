package com.itacademy.dao;

import com.itacademy.entity.Friend;
import com.itacademy.entity.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class FriendDaoTest extends BaseTest {


    @Autowired
    private UserDao userDao;
    @Autowired
    private FriendDao friendDao;

    @Before
    public void init() {
        // ....
    }


    @Test
    public void testSaveFriend() {
        SystemUser sender = new SystemUser();
        SystemUser reciver = new SystemUser();
        userDao.save(sender);
        userDao.save(reciver);
        Friend friend = new Friend("fri", sender, reciver);
        Long friendId = friendDao.save(friend);
        Friend friend1 = friendDao.findById(friendId);
        assertEquals(friend1.getStatus(), "fri");
        assertEquals(friend1.getUserSender(), sender);
        assertEquals(friend1.getUserReceiver(), reciver);
    }

    @Test
    public void testGetFriendById() {
        Friend friend = new Friend();
        Long friendId = friendDao.save(friend);
        Friend friend1 = friendDao.findById(friendId);
        assertThat(friend1, notNullValue());
    }

    @Test
    public void testUpdateFriend() {
        Friend friend = new Friend();
        friend.setStatus("req");
        Long friendId = friendDao.save(friend);
        Friend friend1 = friendDao.findById(friendId);
        assertThat(friend1, notNullValue());
        friend1.setStatus("fri");
        friendDao.update(friend1);
        assertEquals(friend1.getStatus(), "fri");
    }

    @Test
    public void testDeleteFriend() {
        Friend friend = new Friend();
        Long friendId = friendDao.save(friend);
        friendDao.delete(friend);
        Friend friend1 = friendDao.findById(friendId);
        assertThat(friend1, nullValue());
    }

    @Test
    public void testFindAllFriendsByUserId() {
        SystemUser user1 = new SystemUser();
        user1.setName("senderName");
        SystemUser user2 = new SystemUser();
        SystemUser user3 = new SystemUser();
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        Friend friend1 = new Friend("fri", user1, user2);
        Friend friend2 = new Friend("fri", user1, user3);
        friendDao.save(friend1);
        friendDao.save(friend2);
        // List<Friend> results = friendDao.findAllFriendsByUserName("senderName");
        // assertEquals(results.size(), 2);
    }


    @After
    public void finish() {
        // ...
    }
}
