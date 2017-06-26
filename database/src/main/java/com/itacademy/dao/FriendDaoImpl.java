package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.Friend;
import com.itacademy.entity.QFriend;
import com.itacademy.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendDaoImpl extends BaseDaoImpl<Friend> implements FriendDao {

    /**
     * Возвращает имена всех друзей у указанного пользователя со статусом "fri"
     * SELECT u.name, u.email FROM users AS u
     * JOIN friends AS f ON u.id = f.friend_two
     * WHERE f.friend_one = '2' AND f.status = 'fri'
     * ORDER BY (u.name);
     */
    @Override
    public List<Friend> findAllFriendsByUserName(String userName) {
        QFriend friend = new QFriend("myFriend");
        JPAQuery<Friend> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(friend.userReceiver.id, friend.userReceiver.name)
                .from(friend)
                .join(friend.userReceiver)
                .where(friend.status.eq("fri"))
                .where(friend.userSender.name.eq(userName))
                .orderBy(friend.userReceiver.name.asc());
        return query.fetchResults().getResults();
    }

    /**
     * Возвращает имена всех друзей которым отправлен запрос на добавления в друзья со статусом "req""
     */
    @Override
    public List<Friend> findAllMyFriendRequestsSent(String userName) {
        QFriend friend = new QFriend("myFriend");
        JPAQuery<Friend> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(friend.userReceiver.id, friend.userReceiver.name)
                .from(friend)
                .join(friend.userReceiver)
                .where(friend.status.eq("req"))
                .where(friend.userSender.name.eq(userName))
                .orderBy(friend.userReceiver.name.asc());
        return query.fetchResults().getResults();
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
        QFriend friend = new QFriend("myFriend");
        JPAQuery<Friend> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(friend.userReceiver.id, friend.userReceiver.name)
                .from(friend)
                .join(friend.userSender)
                .where(friend.status.eq("req"))
                .where(friend.userReceiver.name.eq(userName))
                .orderBy(friend.userSender.name.asc());
        return query.fetchResults().getResults();
    }


    /**
     * Отправить запрос на добавления в друзья
     * INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 3, 'req');
     */
    @Override
    public Friend sendFriendRequest(Long senderId, Long reciverId) {
        UserDaoImpl userDao = new UserDaoImpl();
        User sender = userDao.findById(senderId);
        User reciver = userDao.findById(reciverId);
        Friend friend = new Friend(sender, reciver, "req");
        getSessionFactory().getCurrentSession().save(friend);
        return friend;
    }

    /**
     * Принять запрос на добавления в друзья
     */
    @Override
    public Friend acceptFriendRequest(Long friendId) {
        FriendDaoImpl friendDao = new FriendDaoImpl();
        Friend friend = friendDao.findById(friendId);
        friend.setStatus("fri");
        getSessionFactory().getCurrentSession().update(friend);
        return friend;
    }


}
