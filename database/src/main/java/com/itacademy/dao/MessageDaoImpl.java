package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.Message;
import com.itacademy.entity.QMessage;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    public List<Message> chatByTwoUsers(Long firstUserId, Long secondUserId) {
        QMessage message = new QMessage("myMessage");
        JPAQuery<Message> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(message/*.userSender.name, message.text, message.creationDate*/)
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

    @Override
    public List<Message> findMessagesByUserName(Long userId) {
        QMessage message = new QMessage("myMessage");
        JPAQuery<Message> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(message)
                .from(message)
                .join(message.userReceiver)
                .join(message.userSender)
                .where(message.userSender.id.eq(userId)
                        .or(message.userReceiver.id.eq(userId)))
                .orderBy(message.creationDate.asc());
        return query.fetchResults().getResults();
    }

    @Override
    public List<String> names(Long userId, String userName) {
        List<Message> messages = findMessagesByUserName(userId);
        Set<String> z = new TreeSet<>();
        for (int i = 0; i < messages.size(); i++) {
            z.add(messages.get(i).getUserReceiver().getName());
            z.add(messages.get(i).getUserSender().getName());
        }
        while (z.contains(userName)) {
            z.remove(userName);
        }
        List<String> list = new ArrayList<>(z);
        return list;
    }
}