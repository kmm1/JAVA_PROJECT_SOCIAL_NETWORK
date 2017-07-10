package com.itacademy.dao;

import com.itacademy.entity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class ProfileDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProfileDao profileDao;


    @Test
    public void saveProfileToUser() {
        SystemUser user = new SystemUser();
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

    @Test
    public void testFindAllProfiles() {
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        profileDao.save(profile1);
        profileDao.save(profile2);
        List<Profile> results = profileDao.findAll();
        assertEquals(results.size(), 2);
    }

    @Test
    public void testFindProfileByUserId() {
        SystemUser user1 = new SystemUser();
        Long userId = userDao.save(user1);
        Profile profile1 = new Profile();
        profile1.setUser(user1);
        profileDao.save(profile1);
        List<Profile> result = profileDao.findProfileByUserId(userId);
        assertEquals(result.size(), 1);
    }

    @Test
    public void testFindOneProfileByUserId() {
        SystemUser user1 = new SystemUser();
        Long userId = userDao.save(user1);
        Profile profile1 = new Profile();
        profile1.setUser(user1);
        profileDao.save(profile1);
        Profile result = profileDao.findOneProfileByUserId(userId);
        assertThat(result, notNullValue());
    }

}