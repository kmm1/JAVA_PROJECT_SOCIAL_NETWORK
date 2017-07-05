package com.itacademy.service;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Comment;
import com.itacademy.entity.SystemUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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
