package com.itacademy.service;

import com.itacademy.entity.Friend;
import com.itacademy.entity.SystemUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class FriendServiceTest extends BaseTest {


    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;


    @Test
    public void testSaveFriend() {
        SystemUser sender = new SystemUser();
        SystemUser reciver = new SystemUser();
        userService.save(sender);
        userService.save(reciver);
        Friend friend = new Friend("fri", sender, reciver);
        Long friendId = friendService.save(friend);
        Friend friend1 = friendService.findById(friendId);
        assertEquals(friend1.getStatus(), "fri");
        assertEquals(friend1.getUserSender(), sender);
        assertEquals(friend1.getUserReceiver(), reciver);
    }

    @Test
    public void testGetFriendById() {
        Friend friend = new Friend();
        Long friendId = friendService.save(friend);
        Friend friend1 = friendService.findById(friendId);
        assertThat(friend1, notNullValue());
    }

    @Test
    public void testUpdateFriend() {
        Friend friend = new Friend();
        friend.setStatus("req");
        Long friendId = friendService.save(friend);
        Friend friend1 = friendService.findById(friendId);
        assertThat(friend1, notNullValue());
        friend1.setStatus("fri");
        friendService.update(friend1);
        assertEquals(friend1.getStatus(), "fri");
    }

    @Test
    public void testDeleteFriend() {
        Friend friend = new Friend();
        Long friendId = friendService.save(friend);
        friendService.delete(friend);
        Friend friend1 = friendService.findById(friendId);
        assertThat(friend1, nullValue());
    }

    @Test
    public void testFindFriendById() {
        Friend friend1 = new Friend();
        Friend friend2 = new Friend();
        Long friendId1 = friendService.save(friend1);
        Long friendId2 = friendService.save(friend2);
        Friend friend = friendService.findById(friendId1);
        assertThat(friend1, notNullValue());
    }

    @Test
    public void testFindAllFriendsByUserId() {
        SystemUser user1 = new SystemUser();
        user1.setName("senderName");
        SystemUser user2 = new SystemUser();
        SystemUser user3 = new SystemUser();
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("fri", user1, user2);
        Friend friend2 = new Friend("fri", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        List<Friend> results = friendService.findAllFriendsByUserName("senderName");
        assertEquals(results.size(), 2);
    }


    @Test
    public void testFindAllMyFriendRequestsSent() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name");
        SystemUser user2 = new SystemUser();
        SystemUser user3 = new SystemUser();
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("req", user1, user2);
        Friend friend2 = new Friend("req", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        List<Friend> results = friendService.findAllMyFriendRequestsSent("Name");
        assertEquals(results.size(), 2);
    }

    @Test
    public void testFindAllMyFriendRequestsReceived() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name");
        SystemUser user2 = new SystemUser();
        user2.setName("secondName");
        SystemUser user3 = new SystemUser();
        user3.setName("thirdName");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("req", user1, user2);
        Friend friend2 = new Friend("req", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        List<Friend> results = friendService.findAllMyFriendRequestsResived("secondName");
        assertEquals(results.size(), 1);
    }

    @Test
    public void testFindOneFriendByUsersNames() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name");
        SystemUser user2 = new SystemUser();
        user2.setName("secondName");
        SystemUser user3 = new SystemUser();
        user3.setName("thirdName");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("req", user1, user2);
        Friend friend2 = new Friend("req", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        Friend result = friendService.findOneFriendByUsersNames("Name", "secondName");
        System.out.println(result);
        assertEquals(result.getUserSender().getName(), "Name");
    }

    @Test
    public void testFindOneFriendByUsersNames2() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name");
        SystemUser user2 = new SystemUser();
        user2.setName("secondName");
        SystemUser user3 = new SystemUser();
        user3.setName("thirdName");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("fri", user1, user2);
        Friend friend2 = new Friend("fri", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        Friend result = friendService.findOneFriendByUsersNames2("Name", "secondName");
        System.out.println(result);
        assertEquals(result.getUserSender().getName(), "Name");
    }

}