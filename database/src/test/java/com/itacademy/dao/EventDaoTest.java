package com.itacademy.dao;

import com.itacademy.entity.*;
import com.itacademy.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import java.util.List;


public class EventDaoTest {

    private SessionFactory sessionFactory;
    FlashmobDao flashmobDao = new FlashmobDao();


    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void testSaveFlashmob() {
        Flashmob flashmob = new Flashmob();
        flashmob.setFlashmobType(EnumFlashmobType.ARTMOB);
        flashmob.setName("raise your hand");
        flashmobDao.saveOne(flashmob);
    }

    @Test
    public void testFindAll() {
        Flashmob flashmob = new Flashmob();
        flashmob.setName("raise your hand");
        flashmobDao.saveOne(flashmob);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Flashmob> resultFl = flashmobDao.findAll();
        List<Event> resultEv = flashmobDao.findAllEvents(session);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(resultFl);
        System.out.println(resultEv);
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void finish() {
        sessionFactory.close();
    }
}
