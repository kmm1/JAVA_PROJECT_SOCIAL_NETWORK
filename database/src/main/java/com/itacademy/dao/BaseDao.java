package com.itacademy.dao;

import com.itacademy.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public abstract class BaseDao<T extends BasicEntity> {

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findOneById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T result = session.get(entityClass, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }


    public void saveOne(T entityClass) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entityClass);
        session.getTransaction().commit();
        session.close();
    }

    public void updateOneById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T entityClass = findOneById(id);
        session.update(entityClass);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteOneById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T entityClass = findOneById(id);
        session.delete(entityClass);
        session.getTransaction().commit();
        session.close();
    }

    public List<T> findAll () {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM " + entityClass, entityClass).list();
    }


}



