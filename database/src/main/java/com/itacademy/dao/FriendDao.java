package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.Friend;

import java.util.List;
import java.util.Optional;


public interface FriendDao extends BaseDao<Friend> {

    List<Friend> findAllFriendsByUserName(String userName);

    List<Friend> findAllMyFriendRequestsSent(String userName);

    List<Friend> findAllMyFriendRequestsResived(String userName);

    Friend findOneFriendByUsersNames(String name1, String name2);


}
