package com.itacademy.service;


import com.itacademy.dao.FriendDao;
import com.itacademy.dao.UserDao;
import com.itacademy.entity.Friend;
import com.itacademy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

    private final FriendDao friendDao;

    @Autowired
    public FriendServiceImpl(FriendDao friendDao) {
        this.friendDao = friendDao;
    }


    @Override
    public Friend findById(Long id) {
        return friendDao.findById(id);
    }

    @Override
    public Long save(Friend friend) {
        return friendDao.save(friend);
    }


    @Override
    public void delete(Friend friend) {
        friendDao.delete(friend);
    }

    @Override
    public List<Friend> findAll() {
        return friendDao.findAll();
    }

    @Override
    public List<Friend> findAllFriendsByUserName(String userName) {
        return friendDao.findAllFriendsByUserName(userName);
    }

    @Override
    public List<Friend> findAllMyFriendRequestsSent(String userName) {
        return friendDao.findAllMyFriendRequestsSent(userName);
    }

    @Override
    public List<Friend> findAllMyFriendRequestsResived(String userName) {
        return friendDao.findAllMyFriendRequestsResived(userName);
    }

    @Override
    public Friend sendFriendRequest(Long senderId, Long reciverId) {
        return friendDao.sendFriendRequest(senderId, reciverId);
    }

    @Override
    public Friend acceptFriendRequest(Long friendId) {
        return friendDao.acceptFriendRequest(friendId);
    }
}
