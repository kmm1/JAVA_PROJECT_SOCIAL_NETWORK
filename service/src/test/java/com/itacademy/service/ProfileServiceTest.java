package com.itacademy.service;

import com.itacademy.entity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class ProfileServiceTest extends BaseTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;


    @Test
    public void saveProfileToUser() {
        SystemUser user = new SystemUser();
        Long userId = userService.save(user);
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        profile.setHomeAddress(new Address("Belarus", "Minsk"));
        profile.setWorkAddress(new Address("Belarus", "Minsk"));
        profile.setMaritalStatus(EnumMaritalStatus.SINGLE);
        profile.setBirthday(new Birthday(1976, 07, 12));
        profile.setUser(user);
        Long profileId = profileService.save(profile);
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
        Long profileId = profileService.save(profile);
        Profile profile1 = profileService.findById(profileId);
        assertThat(profile1, notNullValue());
    }

    @Test
    public void testUpdateProfileById() {
        Profile profile = new Profile();
        profile.setGender(EnumGender.MALE);
        profileService.save(profile);
        assertEquals(profile.getGender().toString(), "MALE");
        profile.setGender(EnumGender.FEMALE);
        profileService.update(profile);
        assertEquals(profile.getGender().toString(), "FEMALE");
    }

    @Test
    public void testFindProfileByUserId() {
        SystemUser user1 = new SystemUser();
        Long userId = userService.save(user1);
        Profile profile1 = new Profile();
        profile1.setUser(user1);
        profileService.save(profile1);
        List<Profile> result = profileService.findProfileByUserId(userId);
        assertEquals(result.size(), 1);
    }

    @Test
    public void testFindOneProfileByUserId() {
        SystemUser user1 = new SystemUser();
        Long userId = userService.save(user1);
        Profile profile1 = new Profile();
        profile1.setUser(user1);
        profileService.save(profile1);
        Profile result = profileService.findOneProfileByUserId(userId);
        assertThat(result, notNullValue());
    }

}