package com.itacademy.service;


import com.itacademy.entity.Message;

import java.util.List;

public interface MessageService {

    Message findById(Long id);

    Long save(Message message);

    List<Message> chatByTwoUsers(Long firstUserId, Long secondUserId);


    List<String> names(Long userId, String userName);


}
