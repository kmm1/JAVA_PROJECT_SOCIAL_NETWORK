package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.Friend;
import com.itacademy.entity.QFriend;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendDaoImpl extends BaseDaoImpl<Friend> implements FriendDao {

    /**
     * Возвращает имена всех друзей у указанного пользователя со статусом "fri"
     */
    @Override
    public List<Friend> findAllFriendsByUserName(String userName) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select f from Friend f join f.userReceiver u join f.userSender us " +
                        "where f.userSender.name=:userName and f.status=:status or f.userReceiver.name=:userName and f.status=:status", Friend.class)
                .setParameter("userName", userName)
                .setParameter("status", "fri")
                .getResultList();
    }


    /**
     * Возвращает имена всех друзей которым отправлен запрос на добавления в друзья со статусом "req""
     */
//    @Override
//    public List<Friend> findAllMyFriendRequestsSent(String userName) {
//        QFriend friend = new QFriend("myFriend");
//        JPAQuery<Friend> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
//        query.select(friend.userReceiver.id, friend.userReceiver.name)
//                .from(friend)
//                .join(friend.userReceiver)
//                .where(friend.status.eq("req"))
//                .where(friend.userSender.name.eq(userName))
//                .orderBy(friend.userReceiver.name.asc());
//        return query.fetchResults().getResults();
//    }
    @Override
    public List<Friend> findAllMyFriendRequestsSent(String userName) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select f from Friend f join f.userReceiver u where f.userSender.name=:userName and f.status=:status order by f.userReceiver.name", Friend.class)
                .setParameter("userName", userName)
                .setParameter("status", "req")
                .getResultList();
    }

    /**
     * Возвращает имена всех друзей от которых пришел запрос на добавления в друзья со статусом "req""
     * SELECT u.name, u.id FROM users AS u
     * JOIN friends AS f ON u.id = f.friend_one
     * WHERE f.friend_two = '2' AND f.status = 'req'
     * ORDER BY (u.name);
     */
    @Override
    public List<Friend> findAllMyFriendRequestsResived(String userName) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select f from Friend f join f.userSender u where f.userReceiver.name=:userName and f.status=:status order by f.userSender.name", Friend.class)
                .setParameter("userName", userName)
                .setParameter("status", "req")
                .getResultList();
    }

    @Override
    public Friend findOneFriendByUsersNames(String name1, String name2) {
        QFriend friend = new QFriend("myFriend");
        JPAQuery<Friend> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(friend)
                .from(friend)
                .join(friend.userSender)
                .join(friend.userReceiver)
                .where(friend.userSender.name.eq(name1)
                        .and(friend.userReceiver.name.eq(name2)
                                .and(friend.status.eq("req")))
                        .or(friend.userSender.name.eq(name2)
                                .and(friend.userReceiver.name.eq(name1)
                                        .and(friend.status.eq("req")))));
        return query.fetchOne();
    }

    @Override
    public Friend findOneFriendByUsersNames2(String name1, String name2) {
        QFriend friend = new QFriend("myFriend");
        JPAQuery<Friend> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(friend)
                .from(friend)
                .join(friend.userSender)
                .join(friend.userReceiver)
                .where(friend.userSender.name.eq(name1)
                        .and(friend.userReceiver.name.eq(name2)
                                .and(friend.status.eq("fri")))
                        .or(friend.userSender.name.eq(name2)
                                .and(friend.userReceiver.name.eq(name1)
                                        .and(friend.status.eq("fri")))));
        return query.fetchOne();
    }

}
