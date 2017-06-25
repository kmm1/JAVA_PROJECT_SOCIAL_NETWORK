package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.Message;
import org.hibernate.Session;

import java.util.List;


public interface MessageDao extends BaseDao<Message> {


    List<Message> chatByTwoUsers(Long firstUserId, Long secondUserId);

}