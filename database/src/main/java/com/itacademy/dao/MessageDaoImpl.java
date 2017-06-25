package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.Message;
import com.itacademy.entity.QMessage;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {


    /**
     * Возвращает сообщения, их автора, дату
     * SELECT u1.name, m.text, m.creation_date
     * FROM messages AS m
     * JOIN users AS u1 ON u1.id = m.user_sender_id
     * JOIN users AS u2 ON u2.id = m.user_receiver_id
     * WHERE user_sender_id=2 AND user_receiver_id = 4
     * OR user_sender_id=4 AND user_receiver_id = 2 ORDER BY (m.creation_date);
     */
    @Override
    public List<Message> chatByTwoUsers( Long firstUserId, Long secondUserId) {
        QMessage message = new QMessage("myMessage");
        JPAQuery<Message> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(message.userSender.name, message.text, message.creationDate)
                .from(message)
                .join(message.userReceiver)
                .join(message.userSender)
                .where(message.userSender.id.eq(firstUserId)
                        .and(message.userReceiver.id.eq(secondUserId))
                        .or(message.userSender.id.eq(secondUserId)
                                .and(message.userReceiver.id.eq(firstUserId))))
                .orderBy(message.creationDate.asc());
        return query.fetchResults().getResults();
    }

}