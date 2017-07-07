package com.itacademy.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class CommentServiceTest extends BaseTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;


    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveComment() {

    }


    @After
    public void finish() {
        // ...
    }
}
