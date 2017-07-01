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
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;


    @Autowired
    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;

    }

    @ModelAttribute("blog")
    public Blog blog() {
        return new Blog();
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }


    @GetMapping(path = "/blog")
    public String showMyFriends(Blog blog, Model model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");

        List test = blogService.findAllUsersBlogs(userId);
        if (test.size() == 0) {
            return "blog-form";
        }
        ArrayList<Blog> findAllBlogsByUserName = (ArrayList<Blog>) blogService.findAllUsersBlogs(userId);
        model.addAttribute("findAllBlogsByUserName", findAllBlogsByUserName);
        return "blog";
    }


    @GetMapping(path = "/readBlog/{blogId}")
    public String readBlog(@PathVariable("blogId") Long blogId, Model model) {
        Blog myBlog = blogService.findById(blogId);
        model.addAttribute("myBlog", myBlog);
        System.out.println(blogId);
        System.out.println(myBlog);
        return "blog-read";
    }

    @GetMapping(path = "/deliteBlog/{blogId}")
    public String showMovieInfo(@PathVariable("blogId") Long blogId, Model model) {
        Blog myBlog = blogService.findById(blogId);
        blogService.delete(myBlog);
        return "redirect:/blog";
    }

    @GetMapping(path = "/addBlog/{blogId}")
    public String changeBlog1(@PathVariable("blogId") Long blogId, Model model) {
        Blog blog = blogService.findById(blogId);
        model.addAttribute("blog", blog);
        model.addAttribute("blogId", blogId);
        return "/blog-form-update";
    }

    @PostMapping(path = "/addBlog/{blogId}")
    public String changeBlog2(@PathVariable("blogId") Long blogId, Model model,
                              @RequestParam String title,
                              @RequestParam String text) {
        Blog blog2 = blogService.findById(blogId);
        System.out.println("title"+title);
        System.out.println("text"+text);
        blog2.setTitle(title);
        blog2.setText(text);
        blogService.update(blog2);
        model.addAttribute("blogId", blogId);
        return "redirect:/readBlog/{blogId}";

    }

    @GetMapping(path = "/saveBlog")
    public String createBlog(Model model, HttpServletRequest req) {
        String userName = (String) req.getSession().getAttribute("userName");
        Long userId = (Long) req.getSession().getAttribute("userId");
        User myUser = userService.findOneUserByName(userName);
        model.addAttribute("myUser", myUser);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(myUser);
        return "blog-form";
    }

    @PostMapping(path = "/saveBlog")
    public String createBlog2(Blog blog, ModelMap model, HttpServletRequest req) {
        String userName = (String) req.getSession().getAttribute("userName");
        User myUser = userService.findOneUserByName(userName);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(myUser);
        blog.setUser(myUser);
        blog.setCreationDate(LocalDateTime.now());
        Long blogId = blogService.save(blog);
        model.addAttribute("blogId", blogId);
        return "redirect:/readBlog/{blogId}";
    }


}
