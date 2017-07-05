package com.itacademy.service;

import com.itacademy.entity.Friend;
import com.itacademy.entity.SystemUser;
import com.itacademy.service.FriendService;
import com.itacademy.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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
