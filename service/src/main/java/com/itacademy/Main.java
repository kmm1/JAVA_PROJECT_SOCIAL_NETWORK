package com.itacademy;

import com.itacademy.config.RootConfig;
import com.itacademy.dao.FriendDao;
import com.itacademy.dao.UserDao;
import com.itacademy.entity.Friend;
import com.itacademy.entity.Profile;
import com.itacademy.entity.User;
import com.itacademy.service.FriendService;
import com.itacademy.service.ProfileService;
import com.itacademy.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        UserService userService = context.getBean(UserService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        FriendService friendService = context.getBean((FriendService.class));


//        List<Friend> x = friendService.findAllFriendsByUserName("vova");
//
//        List<String> list = new ArrayList<String>();
//        ArrayList<String> list2 = new ArrayList<>();

Friend f = friendService.findOneFriendByUsersNames("kate", "vova");
f.setStatus("JJ");
friendService.update(f);
        System.out.println(f);



//        System.out.println(list);
//        System.out.println(x);

    }


}
