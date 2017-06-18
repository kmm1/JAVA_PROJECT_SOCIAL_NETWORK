package com.itacademy.dao;

import com.itacademy.entity.*;
import com.querydsl.jpa.impl.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;


public class UserDao extends BaseDao<User> {
    public UserDao() {
        super(User.class);
    }

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    public List<User> findAll(Session session) {
        QUser user = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(session);
        query.select(user).from(user);
        return query.fetchResults().getResults();
    }

}


