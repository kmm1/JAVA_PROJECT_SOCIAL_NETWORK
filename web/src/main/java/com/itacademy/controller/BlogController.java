package com.itacademy.controller;

import com.itacademy.entity.Blog;
import com.itacademy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(path = "/write-blog")
    public String writeBlog() {
        return "blog-form";
    }

    @PostMapping(path = "/write-blog")
    public String saveBlog(Blog blog, Model model) {
        Long id = blogService.save(blog);
        model.addAttribute("userId", id);
        return "registration-form";
    }


}
