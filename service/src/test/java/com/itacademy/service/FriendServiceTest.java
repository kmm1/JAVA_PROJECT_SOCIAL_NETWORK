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
        sender.setName("anothcccerName");
        sender.setEmail("emvvcccail111@gmail.com");
        reciver.setName("acnotherName");
        reciver.setEmail("emdfdvvail111@gmail.com");
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
        user1.setName("senderNamebv");
        user1.setEmail("emvvail111uu@gmail.com");
        SystemUser user2 = new SystemUser();
        SystemUser user3 = new SystemUser();
        user2.setName("b");
        user2.setEmail("b@gmail.com");
        user3.setName("bb");
        user3.setEmail("bb@gmail.com");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("fri", user1, user2);
        Friend friend2 = new Friend("fri", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        List<Friend> results = friendService.findAllFriendsByUserName("senderNamebv");
        assertEquals(results.size(), 2);
    }


    @Test
    public void testFindAllMyFriendRequestsSent() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name959");
        user1.setEmail("emvvail111959@gmail.com");
        SystemUser user2 = new SystemUser();
        SystemUser user3 = new SystemUser();
        user2.setName("yy");
        user2.setEmail("yy@gmail.com");
        user3.setName("r5");
        user3.setEmail("r5@gmail.com");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("req", user1, user2);
        Friend friend2 = new Friend("req", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        List<Friend> results = friendService.findAllMyFriendRequestsSent("Name959");
        assertEquals(results.size(), 2);
    }

    @Test
    public void testFindAllMyFriendRequestsReceived() {
        SystemUser user1 = new SystemUser();
        user1.setName("Name");
        user1.setEmail("emvvafdil111@gmail.com");
        SystemUser user2 = new SystemUser();
        user2.setName("secofbndName0");
        user2.setEmail("emvvaifl111@gmail.com");
        SystemUser user3 = new SystemUser();
        user3.setName("thirjldName");
        user3.setEmail("emvvail1j11@gmail.com");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("req", user1, user2);
        Friend friend2 = new Friend("req", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        List<Friend> results = friendService.findAllMyFriendRequestsResived("secofbndName0");
        assertEquals(results.size(), 1);
    }

    @Test
    public void testFindOneFriendByUsersNames() {
        SystemUser user1 = new SystemUser();
        user1.setName("Nameqq");
        user1.setEmail("eqqmvvail111@gmail.com");
        SystemUser user2 = new SystemUser();
        user2.setName("secondNameqq");
        user2.setEmail("emvvaqil111@gmail.com");
        SystemUser user3 = new SystemUser();
        user3.setName("thirdNameqq");
        user3.setEmail("emqil111@gmail.com");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("req", user1, user2);
        Friend friend2 = new Friend("req", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        Friend result = friendService.findOneFriendByUsersNames("Nameqq", "secondNameqq");
        System.out.println(result);
        assertEquals(result.getUserSender().getName(), "Nameqq");
    }

    @Test
    public void testFindOneFriendByUsersNames2() {
        SystemUser user1 = new SystemUser();
        user1.setName("Namez");
        user1.setEmail("z@gmail.com");
        SystemUser user2 = new SystemUser();
        user2.setName("secondNamez");
        user2.setEmail("zz@gmail.com");
        SystemUser user3 = new SystemUser();
        user3.setName("thirdNamez");
        user3.setEmail("zzz@gmail.com");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        Friend friend1 = new Friend("fri", user1, user2);
        Friend friend2 = new Friend("fri", user1, user3);
        friendService.save(friend1);
        friendService.save(friend2);
        Friend result = friendService.findOneFriendByUsersNames2("Namez", "secondNamez");
        System.out.println(result);
        assertEquals(result.getUserSender().getName(), "Namez");
    }
}