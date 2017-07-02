package com.itacademy;

import com.itacademy.config.RootConfig;
import com.itacademy.dao.FriendDao;
import com.itacademy.dao.UserDao;
import com.itacademy.entity.*;
import com.itacademy.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        UserService userService = context.getBean(UserService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        FriendService friendService = context.getBean((FriendService.class));
        BlogService blogService = context.getBean((BlogService.class));
        MessageService messageService = context.getBean((MessageService.class));
        CommentService commentService = context.getBean((CommentService.class));


        List<Comment> allCommentsByBlogId = commentService.findAllCommentsByBlogId(1L);


        System.out.println(allCommentsByBlogId);


//        List<Friend> x = friendService.findAllFriendsByUserName("vova");
//
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
