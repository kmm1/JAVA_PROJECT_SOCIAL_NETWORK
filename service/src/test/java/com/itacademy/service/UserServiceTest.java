package com.itacademy.service;

import com.itacademy.entity.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveUser() {
        SystemUser user = new SystemUser();
        userService.save(user);
    }


    @After
    public void finish() {
        // ...
    }
}

