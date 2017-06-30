package com.itacademy.controller;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Friend;
import com.itacademy.entity.Profile;
import com.itacademy.entity.User;
import com.itacademy.service.BlogService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final BlogService blogService;
    private final UserService userService;


    @Autowired
    public MainController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping(path = "/mainPageUser")
    public String hh( Model model) {
        return "/main-page-user";
    }


}
