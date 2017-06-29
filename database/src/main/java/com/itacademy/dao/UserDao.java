package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.Profile;
import com.itacademy.entity.User;

import java.util.List;


public interface UserDao extends BaseDao<User> {

    List<User> findUserByNamePassword(String name, String password);

    User findOneUserByName(String name);

}
