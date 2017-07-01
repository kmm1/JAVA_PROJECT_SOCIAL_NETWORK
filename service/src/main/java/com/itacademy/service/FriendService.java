package com.itacademy.service;

import com.itacademy.entity.Friend;

import java.util.List;

public interface FriendService {

    Friend findById(Long id);

    Long save(Friend friend);

    void delete(Friend friend);

    List<Friend> findAll();

    List<Friend> findAllFriendsByUserName(String userName);

    List<Friend> findAllMyFriendRequestsSent(String userName);

    List<Friend> findAllMyFriendRequestsResived(String userName);

    Friend findOneFriendByUsersNames(String name1, String name2);

    void update(Friend friend);

    Friend findOneFriendByUsersNames2(String name1, String name2);


}
