package com.itacademy;

import com.itacademy.config.RootConfig;
import com.itacademy.entity.Profile;
import com.itacademy.entity.User;
import com.itacademy.service.ProfileService;
import com.itacademy.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        UserService userService = context.getBean(UserService.class);
        ProfileService profileService = context.getBean(ProfileService.class);

        Long userId = 2L;
        String userName = "vova";
        List<Profile> profile = profileService.findProfileByUserId(userId);
        profile.get(0).setAboutMe("kite");
        profileService.update( profile.get(0));
        System.out.println(profile.get(0));

    }


    }
