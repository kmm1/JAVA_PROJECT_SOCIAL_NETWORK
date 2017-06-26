package com.itacademy.dao.common;

import com.itacademy.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.util.List;

public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private final Class<T> modelClass;


    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.modelClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }


    @Override
    public Long save(T entity) {
        return (Long) sessionFactory.getCurrentSession().save(entity);
    }


    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }


    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }


    @Override
    public T findById(Long id) {
        return sessionFactory.getCurrentSession().get(modelClass, id);
    }

    @Override
    public T findByName(String name) {
        return sessionFactory.getCurrentSession().get(modelClass, name);
    }


    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                "from " + modelClass.getSimpleName(), modelClass)
                .getResultList();
    }


    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

