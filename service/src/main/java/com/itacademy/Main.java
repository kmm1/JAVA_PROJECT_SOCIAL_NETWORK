package com.itacademy;

import com.itacademy.config.RootConfig;
import com.itacademy.entity.User;
import com.itacademy.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        UserService userService = context.getBean(UserService.class);


        List<User> user = userService.findUserByNamePassword("kate", "smth");

        System.out.println(user.get(0).getId());
    }
}
