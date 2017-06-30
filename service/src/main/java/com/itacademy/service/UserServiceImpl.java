package com.itacademy.service;

import com.itacademy.dao.UserDao;
import com.itacademy.entity.Friend;
import com.itacademy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User findOneUserByName(String name) {
        return userDao.findOneUserByName(name);
    }

    @Override
    public List<User> findOneUserByName2(String name) {
        return userDao.findOneUserByName2(name);
    }


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Long save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findUserByNamePassword(String name, String password) {
        return userDao.findUserByNamePassword(name, password);
    }



}
