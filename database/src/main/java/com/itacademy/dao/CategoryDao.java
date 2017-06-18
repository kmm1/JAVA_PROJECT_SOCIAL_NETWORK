package com.itacademy.dao;

import com.itacademy.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static com.itacademy.entity.EnumCategory.BUSINESS;

/**
 * Created by Tom on 14.06.2017.
 */
public class CategoryDao extends BaseDao<Category> {
    public CategoryDao() {
        super(Category.class);
    }

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    /**
     *
     */
    public List<Category> findAllCategories(Session session) {
        QCategory category = new QCategory("myCategory");
        JPAQuery<Category> query = new JPAQuery<>(session);
        query.select(category).from(category);
        return query.fetchResults().getResults();
    }

    /**
     * Найти названия даты всех блогов в выбранной категории по ее id
     */


}
