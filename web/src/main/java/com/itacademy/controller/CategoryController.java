package com.itacademy.controller;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Category;
import com.itacademy.entity.SystemUser;
import com.itacademy.service.BlogService;
import com.itacademy.service.CategoryService;
import com.itacademy.service.CommentService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class CategoryController {

    private final BlogService blogService;
    private final UserService userService;
    private final CommentService commentService;
    private final CategoryService categoryService;


    @Autowired
    public CategoryController(BlogService blogService, UserService userService,
                              CommentService commentService, CategoryService categoryService) {
        this.blogService = blogService;
        this.userService = userService;
        this.commentService = commentService;
        this.categoryService = categoryService;

    }


}
