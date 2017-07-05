package com.itacademy.service;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Category;
import com.itacademy.entity.SystemUser;
import com.itacademy.service.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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
