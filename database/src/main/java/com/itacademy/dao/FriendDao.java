package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.Friend;
import org.hibernate.Session;

import java.util.List;


public interface FriendDao extends BaseDao<Friend> {

    List<Friend> findAllFriendsByUserName(String userName, String friendsStatus);

    Friend sendFriendRequest(Long senderId, Long reciverId);

    Friend acceptFriendRequest(Long friendId);


}
