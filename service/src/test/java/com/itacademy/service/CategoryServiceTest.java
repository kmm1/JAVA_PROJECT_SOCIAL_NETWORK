package com.itacademy.service;

import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CategoryServiceTest extends BaseTest {


    @Autowired
    private CategoryService categoryService;


    @Before
    public void init() {
        // ....
    }

    @Test
    public void saveCategory() {

    }


    @After
    public void finish() {
        // ...
    }
}

