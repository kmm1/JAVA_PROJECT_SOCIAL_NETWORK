package com.itacademy.service;

import com.itacademy.entity.EnumFlashmobType;
import com.itacademy.entity.Event;
import com.itacademy.entity.Flashmob;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class EventServiceTest extends BaseTest {

    @Autowired
    private FlashmobService flashmobService;

    @Test
    public void testSaveFlashmob() {
        Flashmob flashmob = new Flashmob();
        flashmob.setFlashmobType(EnumFlashmobType.ARTMOB);
        flashmob.setName("raise your hand");
        flashmob.setAboutEvent("description test");
        flashmob.setCreationDate(LocalDateTime.now());
        flashmobService.save(flashmob);
    }

    @Test
    public void testFindAll() {
        Flashmob flashmob1 = new Flashmob();
        Flashmob flashmob2 = new Flashmob();
        flashmob1.setName("raise your hand");
        flashmob2.setName("books story");
        flashmobService.save(flashmob1);
        flashmobService.save(flashmob2);
        List<Flashmob> resultFl = flashmobService.findAll();
        List<Event> resultEv = flashmobService.findAllEvents();
        assertEquals(resultFl.size(), 2);
    }

    @Test
    public void testGetFlashmobById() {
        Flashmob flashmob = new Flashmob();
        flashmob.setName("name");
        Long flashmobId = flashmobService.save(flashmob);
        Flashmob flashmob1 = flashmobService.findById(flashmobId);
        assertThat(flashmob1, notNullValue());
    }

    @Test
    public void testUpdateFlashmob() {
        Flashmob flashmob = new Flashmob();
        flashmob.setName("firstName");
        Long flashmobId = flashmobService.save(flashmob);
        assertEquals(flashmob.getName(), "firstName");
        Flashmob flashmob1 = flashmobService.findById(flashmobId);
        flashmob1.setName("anotherName");
        flashmobService.update(flashmob1);
        assertEquals(flashmob1.getName(), "anotherName");
    }

    @Test
    public void testDeleteFlashmob() {
        Flashmob flashmob = new Flashmob();
        Long flashmobId = flashmobService.save(flashmob);
        flashmobService.delete(flashmob);
        Flashmob flashmob1 = flashmobService.findById(flashmobId);
        assertThat(flashmob1, nullValue());
    }
}
