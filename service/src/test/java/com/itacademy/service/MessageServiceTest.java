package com.itacademy.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MessageServiceTest extends BaseTest {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveMessage() {
    }


    @After
    public void finish() {
        // ...
    }
}