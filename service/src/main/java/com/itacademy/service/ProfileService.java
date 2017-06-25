package com.itacademy.service;

import com.itacademy.entity.Profile;
import com.itacademy.entity.User;


public interface ProfileService {

    Profile findById(Long id);

    Long save(Profile profile);
}
