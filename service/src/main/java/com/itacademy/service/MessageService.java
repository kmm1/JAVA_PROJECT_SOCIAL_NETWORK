package com.itacademy.service;


import com.itacademy.entity.Message;
import com.itacademy.service.common.BaseService;

import java.util.List;

public interface MessageService extends BaseService<Message> {

    Message findById(Long id);

    Long save(Message message);

    List<Message> chatByTwoUsers(Long firstUserId, Long secondUserId);

    List<String> names(Long userId, String userName);
}
