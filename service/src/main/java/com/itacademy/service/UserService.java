package com.itacademy.service;

import com.itacademy.entity.Friend;
import com.itacademy.entity.Profile;
import com.itacademy.entity.User;

import java.util.List;


public interface UserService {

    User findById(Long id);

    Long save(User user);

    List<User> findAll();

    List<User> findUserByNamePassword(String name, String password);

    User findOneUserByName(String name);

    List<User> findOneUserByName2 (String name);


}

