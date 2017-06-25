package com.itacademy.service;

import com.itacademy.dao.ProfileDao;
import com.itacademy.dao.UserDao;
import com.itacademy.entity.Profile;
import com.itacademy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;

    @Autowired
    public ProfileServiceImpl(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public Profile findById(Long id) {
        return profileDao.findById(id);
    }

    @Override
    public Long save(Profile profile) {
        return profileDao.save(profile);

    }

}
