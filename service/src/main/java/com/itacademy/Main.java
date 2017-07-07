package com.itacademy;

import com.itacademy.config.RootConfig;
import com.itacademy.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        UserService userService = context.getBean(UserService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        FriendService friendService = context.getBean((FriendService.class));
        BlogService blogService = context.getBean((BlogService.class));
        MessageService messageService = context.getBean((MessageService.class));
        CommentService commentService = context.getBean((CommentService.class));
        CategoryService categoryService = context.getBean((CategoryService.class));

        Integer numberOfBlogs = blogService.countUserBlogs(1L);
        Integer a = (int) Math.ceil((double) numberOfBlogs / (double) 5);
        System.out.println(a);
        Integer numberOfPages = (int) Math.ceil(numberOfBlogs / 5);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < numberOfPages + 1; i++) {
            numbers.add(i);
        }
        System.out.println(numberOfBlogs);
        System.out.println(numberOfPages);
        System.out.println(numbers);


//        List<Friend> x = friendService.findAllFriendsByUserName("vova");
//        List<String> list = new ArrayList<String>();
//        ArrayList<String> list2 = new ArrayList<>();

//friendService.delete(f);
//        Friend f = friendService.findById(40L);
//        System.out.println(f);
//        Friend ff = friendService.findOneFriendByUsersNames2("vova", "kate");
//        System.out.println(ff);
//
//        friendService.delete(ff);


//        System.out.println(list);
//        System.out.println(x);

    }


}
