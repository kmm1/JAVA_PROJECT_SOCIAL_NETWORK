package com.itacademy.service;

import com.itacademy.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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