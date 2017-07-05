package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.QUser;
import com.itacademy.entity.Role;
import com.itacademy.entity.SystemUser;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl extends BaseDaoImpl<SystemUser> implements UserDao {

    @Override
    public List<SystemUser> findUserByNamePassword(String name, String password) {
        QUser user = new QUser("myUser");
        JPAQuery<SystemUser> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(user)
                .from(user)
                .where(user.name.eq(name))
                .where(user.password.eq(password));
        return query.fetchResults().getResults();
    }

    @Override
    public SystemUser findOneUserByName(String name) {
        QUser user = new QUser("myUser");
        JPAQuery<SystemUser> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(user)
                .from(user)
                .where(user.name.eq(name));
        return query.fetchOne();
    }

    @Override
    public List<SystemUser> findOneUserByName2(String name) {
        QUser user = new QUser("myUser");
        JPAQuery<SystemUser> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(user)
                .from(user)
                .where(user.name.eq(name));
        return query.fetchResults().getResults();
    }

    @Override
    public SystemUser findByName(String name) {
        QUser user = new QUser("myUser");
        JPAQuery<SystemUser> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(user)
                .from(user)
                .where(user.name.eq(name));
        List<SystemUser> results = query.fetchResults().getResults();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    public void addExistingRoleToExistingUser(Long roleId, Long userId) {
        SystemUser user = getSessionFactory().getCurrentSession().get(SystemUser.class, userId);
        Role role = getSessionFactory().getCurrentSession().get(Role.class, roleId);
        user.getRoles().add(role);
    }


}
