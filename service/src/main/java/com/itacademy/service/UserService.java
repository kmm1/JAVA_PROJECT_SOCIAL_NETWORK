package com.itacademy.service;

import com.itacademy.entity.User;

import java.util.List;


public interface UserService {

    User findById(Long id);

    User findByName(String name);

    Long save(User user);

    List<User> findUserByNamePassword(String name, String password);

}

