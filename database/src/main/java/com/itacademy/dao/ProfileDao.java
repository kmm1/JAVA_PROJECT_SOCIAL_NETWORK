package com.itacademy.dao;

import com.itacademy.entity.Profile;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Tom on 10.06.2017.
 */
public class ProfileDao extends BaseDao<Profile> {
    public ProfileDao() {
        super(Profile.class);
    }

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();
}