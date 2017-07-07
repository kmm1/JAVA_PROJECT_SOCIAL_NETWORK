package com.itacademy.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ProfileServiceTest extends BaseTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;

    @Before
    public void init() {
        // ....
    }


    @Test
    public void deleteOneById() {

    }

    @After
    public void finish() {
        // ...
    }
}