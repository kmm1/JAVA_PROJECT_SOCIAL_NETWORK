package com.itacademy.dao;

import com.itacademy.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class ProfileDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProfileDao profileDao;

    @Before
    public void init() {
        // ....
    }

    @Test
    public void saveProfileToUser() {
        User user = new User();
        Long userId = userDao.save(user);
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        profile.setHomeAddress(new Address("Belarus", "Minsk"));
        profile.setWorkAddress(new Address("Belarus", "Minsk"));
        profile.setMaritalStatus(EnumMaritalStatus.SINGLE);
        profile.setBirthday(new Birthday(1976, 07, 12));
        profile.setUser(user);
        Long profileId = profileDao.save(profile);
        assertEquals(profile.getId(), profileId);
        assertEquals(profile.getGender().toString(), "MALE");
        assertEquals(profile.getHomeAddress(), (new Address("Belarus", "Minsk")));
        assertEquals(profile.getWorkAddress(), (new Address("Belarus", "Minsk")));
        assertEquals(profile.getMaritalStatus().toString(), "SINGLE");
        assertEquals(profile.getBirthday(), (new Birthday(1976, 07, 12)));
        assertEquals(profile.getUser().getId(), userId);
    }

    @Test
    public void testGetProfileById() {
        Profile profile = new Profile();
        Long profileId = profileDao.save(profile);
        Profile profile1 = profileDao.findById(profileId);
        assertThat(profile1, notNullValue());
    }

    @Test
    public void testUpdateProfileById() {
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        profileDao.save(profile);
        assertEquals(profile.getGender().toString(), "MALE");
        profile.setGender(EnumGender.FEMALE);
        profileDao.update(profile);
        assertEquals(profile.getGender().toString(), "FEMALE");
    }

    @Test
    public void deleteOneById() {
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        Long profileId = profileDao.save(profile);
        profileDao.delete(profile);
        Profile profile1 = profileDao.findById(profileId);
        assertThat(profile1, nullValue());
    }

    @After
    public void finish() {
        // ...
    }
}