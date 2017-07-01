package com.itacademy.dao;

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


public class EventDaoTest extends BaseTest {


    @Autowired
    private FlashmobDao flashmobDao;


    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveFlashmob() {
        Flashmob flashmob = new Flashmob();
        flashmob.setFlashmobType(EnumFlashmobType.ARTMOB);
        flashmob.setName("raise your hand");
        flashmob.setAboutEvent("description test");
        flashmob.setCreationDate(LocalDateTime.now());
        flashmobDao.save(flashmob);
    }

    @Test
    public void testFindAll() {
        Flashmob flashmob1 = new Flashmob();
        Flashmob flashmob2 = new Flashmob();
        flashmob1.setName("raise your hand");
        flashmob2.setName("books story");
        flashmobDao.save(flashmob1);
        flashmobDao.save(flashmob2);
        List<Flashmob> resultFl = flashmobDao.findAll();
        List<Event> resultEv = flashmobDao.findAllEvents();
        assertEquals(resultFl.size(), 2);
    }

    @After
    public void finish() {
        // ...
    }
}
