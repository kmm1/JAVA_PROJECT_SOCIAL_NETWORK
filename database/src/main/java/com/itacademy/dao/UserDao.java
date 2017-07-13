package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.SystemUser;

import java.util.List;


public interface UserDao extends BaseDao<SystemUser> {

    List<SystemUser> findUserByNamePassword(String name, String password);

    SystemUser findOneUserByName(String name);

    List<SystemUser> findOneUserByName2(String name);

    SystemUser findByName(String name);

    void addExistingRoleToExistingUser(Long roleId, Long userId);
}
