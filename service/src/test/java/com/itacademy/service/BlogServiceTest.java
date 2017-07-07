package com.itacademy.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class BlogServiceTest extends BaseTest {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;


    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveUser() {

    }

    @After
    public void finish() {
        // ...
    }
}
