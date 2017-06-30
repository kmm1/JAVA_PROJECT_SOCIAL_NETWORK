package com.itacademy.service;


import com.itacademy.dao.MessageDao;
import com.itacademy.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }


    @Override
    public Message findById(Long id) {
        return messageDao.findById(id);
    }

    @Override
    public Long save(Message message) {
        return messageDao.save(message);
    }
}