package com.itacademy.service;

import com.itacademy.entity.Message;
import com.itacademy.entity.User;
import com.itacademy.service.MessageService;
import com.itacademy.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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