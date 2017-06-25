package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {

    /**
     * SELECT *
     * FROM profiles AS p
     * JOIN users AS u ON  u.id = p.user_id
     * WHERE u.id = 2;
     */
    @Override
    public List<Profile> findProfileByUserId(Long userId) {
        QProfile profile = new QProfile("myProfile");
        JPAQuery<Profile> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(profile)
                .from(profile)
                .join(profile.user)
                .where(profile.user.id.eq(userId));
        return query.fetchResults().getResults();
    }

}
