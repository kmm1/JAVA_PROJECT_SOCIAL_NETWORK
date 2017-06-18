package com.itacademy.dao;

import com.itacademy.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Tom on 10.06.2017.
 */
public class FriendDao extends BaseDao<Friend> {
    public FriendDao() {
        super(Friend.class);
    }

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    /**
     * Возвращает имена всех друзей у указанного пользователя
     * SELECT u.name, u.email FROM users AS u
     * JOIN friends AS f ON u.id = f.friend_two
     * WHERE f.friend_one = '2' AND f.status = 'fri'
     * ORDER BY (u.name);
     */
    public List<Friend> findAllFriendsByUserId(Session session, String userName, String friendsStatus) {
        QFriend friend = new QFriend("myFriend");
        JPAQuery<Friend> query = new JPAQuery<>(session);
        query.select(friend.userReceiver.name, friend.status)
                .from(friend)
                .join(friend.userReceiver)
                .where(friend.status.eq(friendsStatus))
                .where(friend.userSender.name.eq(userName))
                .orderBy(friend.userReceiver.name.asc());
        return query.fetchResults().getResults();
    }

    /**
     * Отправить запрос на добавления в друзья
     * INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 3, 'req');
     */
    public static Friend sendFriendRequest(Session session, Long senderId, Long reciverId) {
        UserDao userDao = new UserDao();
        User sender = userDao.findOneById(senderId);
        User reciver = userDao.findOneById(reciverId);
        Friend friend = new Friend();
        friend.setUserSender(sender);
        friend.setUserReceiver(reciver);
        friend.setStatus("req");
        session.save(friend);
        return friend;
    }

    /**
     * Принять запрос на добавления в друзья
     */
    public static Friend acceptFriendRequest(Session session, Long friendId) {
        FriendDao friendDao = new FriendDao();
        Friend friend = friendDao.findOneById(friendId);
        friend.setStatus("fri");
        session.update(friend);
        return friend;
    }


    /**
     * Удалить из друзей (deleteOne basicDao)
     */


}
