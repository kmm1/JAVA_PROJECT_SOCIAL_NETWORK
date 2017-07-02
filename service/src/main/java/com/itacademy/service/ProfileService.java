package com.itacademy.service;

import com.itacademy.entity.Profile;

import java.util.List;


public interface ProfileService {

    Long save(Profile profile);

    Profile findById(Long id);

    void update(Profile profile);


    List<Profile> findProfileByUserId(Long userId);

    Profile findOneProfileByUserId(Long userId);

}
