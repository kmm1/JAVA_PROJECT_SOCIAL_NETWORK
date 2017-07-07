package com.itacademy.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class FriendServiceTest extends BaseTest {


    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    @Before
    public void init() {
        // ....
    }


    @Test
    public void testGetFriendById() {
    }


    @After
    public void finish() {
        // ...
    }
}
