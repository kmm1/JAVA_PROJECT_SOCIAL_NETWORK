package com.itacademy.dao;

import com.itacademy.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * Created by Tom on 10.06.2017.
 */
public class MessageDao extends BaseDao<Message> {
    public MessageDao() {
        super(Message.class);
    }

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();


    /**
     * Возвращает сообщения, их автора, дату
     * SELECT u1.name, m.text, m.creation_date
     * FROM messages AS m
     * JOIN users AS u1 ON u1.id = m.user_sender_id
     * JOIN users AS u2 ON u2.id = m.user_receiver_id
     * WHERE user_sender_id=2 AND user_receiver_id = 4
     * OR user_sender_id=4 AND user_receiver_id = 2
     * ORDER BY (m.creation_date);
     */
    public List<Message> chatByTwoUsers(Session session, Long firstUserId, Long seconfUserId) {
        QMessage message = new QMessage("myMessage");
        JPAQuery<Message> query = new JPAQuery<>(session);
        query.select(message.userSender.name, message.text, message.creationDate)
                .from(message)
                .join(message.userReceiver)
                .join(message.userSender)
                .where(message.userSender.id.eq(firstUserId)
                        .and(message.userReceiver.id.eq(seconfUserId))
                        .or(message.userSender.id.eq(seconfUserId)
                                .and(message.userReceiver.id.eq(firstUserId))))
                .orderBy(message.creationDate.asc());
        return query.fetchResults().getResults();
    }

}