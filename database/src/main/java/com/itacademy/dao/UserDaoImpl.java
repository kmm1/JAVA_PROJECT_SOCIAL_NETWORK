package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.QUser;
import com.itacademy.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public List<User> findUserByNamePassword(String name, String password) {
        QUser user = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(user)
                .from(user)
                .where(user.name.eq(name))
                .where(user.password.eq(password));
        return query.fetchResults().getResults();
    }

    @Override
    public User findOneUserByName (String name) {
        QUser user = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(user)
                .from(user)
                .where(user.name.eq(name));
        return query.fetchOne();
    }

    public List<User> findOneUserByName2 (String name) {
        QUser user = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(user)
                .from(user)
                .where(user.name.eq(name));
        return query.fetchResults().getResults();
    }
}
