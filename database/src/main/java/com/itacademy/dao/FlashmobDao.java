package com.itacademy.dao;

import com.itacademy.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * Created by Tom on 16.06.2017.
 */
public class FlashmobDao extends BaseDao<Flashmob> {
    public FlashmobDao() {
        super(Flashmob.class);
    }

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    public List<Flashmob> findAllFlashmobs(Session session) {
        return session
                .createQuery("from Flashmob", Flashmob.class)
                .getResultList();
    }

    public List<Event> findAllEvents(Session session) {
        return session
                .createQuery("from Event ", Event.class)
                .getResultList();
    }

}