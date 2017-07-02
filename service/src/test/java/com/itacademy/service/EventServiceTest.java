package com.itacademy.service;

import com.itacademy.entity.EnumFlashmobType;
import com.itacademy.entity.Event;
import com.itacademy.entity.Flashmob;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class EventServiceTest extends BaseTest {


    @Autowired
    private FlashmobService flashmobService;


    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveFlashmob() {
    }


    @After
    public void finish() {
        // ...
    }
}
