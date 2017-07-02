package com.itacademy.controller;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Category;
import com.itacademy.entity.User;
import com.itacademy.service.BlogService;
import com.itacademy.service.CategoryService;
import com.itacademy.service.CommentService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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

    @ModelAttribute("blog")
    public Blog blog() {
        return new Blog();
    }

    @ModelAttribute("category")
    public Category category() {
        return new Category();
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @ModelAttribute("allCategories")
    public List<Category> categories() {
        return categoryService.findAll();
    }



}
