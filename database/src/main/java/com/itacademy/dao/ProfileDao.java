package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.Profile;

import java.util.List;


public interface ProfileDao extends BaseDao<Profile> {

    List<Profile> findProfileByUserId(Long userId);

    Profile findOneProfileByUserId(Long userId);


}