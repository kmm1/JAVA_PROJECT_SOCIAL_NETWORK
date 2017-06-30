package com.itacademy.service;


import com.itacademy.entity.Blog;
import com.itacademy.entity.Message;

public interface MessageService {

    Message findById(Long id);

    Long save(Message message);
}
