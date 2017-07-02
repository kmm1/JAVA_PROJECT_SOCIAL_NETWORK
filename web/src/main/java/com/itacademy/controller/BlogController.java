package com.itacademy.controller;

import com.itacademy.entity.*;
import com.itacademy.service.BlogService;
import com.itacademy.service.CategoryService;
import com.itacademy.service.CommentService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;
    private final CommentService commentService;
    private final CategoryService categoryService;


    @Autowired
    public BlogController(BlogService blogService, UserService userService,
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

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @ModelAttribute("allCategories")
    public List<Category> categories() {
        return categoryService.findAll();
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
    public String readBlog(@PathVariable("blogId") Long blogId, Model model, HttpServletRequest req) {
        String userName = (String) req.getSession().getAttribute("userName");
        Long userId = (Long) req.getSession().getAttribute("userId");
        Blog myBlog = blogService.findById(blogId);
        List<Comment> allCommentsByBlogId = commentService.findAllCommentsByBlogId(blogId);
        model.addAttribute("userName", userName);
        model.addAttribute("allCommentsByBlogId", allCommentsByBlogId);
        model.addAttribute("myBlog", myBlog);
        System.out.println(blogId);
        System.out.println(myBlog);
        return "blog-read";
    }

    @GetMapping(path = "/deliteBlog/{blogId}")
    public String deliteMovie(@PathVariable("blogId") Long blogId, Model model) {
        Blog myBlog = blogService.findById(blogId);
        List<Category> allCategoriesByBlogId = categoryService.findAllCategoriesByBlogId(blogId);
        List<Comment> allCommentsByBlogId = commentService.findAllCommentsByBlogId(blogId);
        if (allCategoriesByBlogId.size() != 0) {
            System.out.println("RRRRRRRRRRRRRRRRRR");
            for (int i = 0; i < allCategoriesByBlogId.size(); i++) {
                Long categoryId = allCategoriesByBlogId.get(i).getId();
                blogService.deliteExistingBlogFromExistingCategory(categoryId, blogId);
            }
        }
        if (allCommentsByBlogId.size() != 0) {
            System.out.println("SSSSSSSSSSSSSSSSSSS");
            for (int i = 0; i < allCommentsByBlogId.size(); i++) {
                Long commentId = allCommentsByBlogId.get(i).getId();
                Comment comment = commentService.findById(commentId);
                commentService.delete(comment);
            }
        }
        System.out.println("HHHHHHHHHHHHHHHHHHHHH");
        blogService.delete(myBlog);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX");
        return "redirect:/blog";
    }

    @GetMapping(path = "/addBlog/{blogId}")
    public String changeBlog1(@PathVariable("blogId") Long blogId, Model model) {
        Blog blog = blogService.findById(blogId);
        List<Category> allCategoriesByBlogId = categoryService.findAllCategoriesByBlogId(blogId);
        model.addAttribute("allCategoriesByBlogId", allCategoriesByBlogId);
        model.addAttribute("blog", blog);
        model.addAttribute("blogId", blogId);
        return "/blog-form-update";
    }

    @PostMapping(path = "/addBlog/{blogId}")
    public String changeBlog2(@PathVariable("blogId") Long blogId, Model model,
                              @RequestParam String title,
                              @RequestParam String text) {
        Blog blog2 = blogService.findById(blogId);
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

    @PostMapping(path = "/addExistingBlogToExistingCategory/{blogId}")
    public String addExistingBlogToExistingCategory1
            (@PathVariable("blogId") Long blogId, Model model,
             @RequestParam EnumCategory name) {
        Blog blog2 = blogService.findById(blogId);
        List<Category> oneCategoryByEnumCategory = categoryService.findOneCategoryByEnumCategory(name);
        Long categoryId = oneCategoryByEnumCategory.get(0).getId();
        blogService.addExistingBlogToExistingCategory(categoryId, blogId);
        return "redirect:/addBlog/{blogId}";
    }

    @PostMapping(path = "/deliteExistingBlogFromExistingCategory/{blogId}")
    public String deliteExistingBlogFromExistingCategory1
            (@PathVariable("blogId") Long blogId, Model model,
             @RequestParam EnumCategory name2) {
        Blog blog2 = blogService.findById(blogId);
        List<Category> oneCategoryByEnumCategory = categoryService.findOneCategoryByEnumCategory(name2);
        Long categoryId = oneCategoryByEnumCategory.get(0).getId();
        blogService.deliteExistingBlogFromExistingCategory(categoryId, blogId);
        return "redirect:/addBlog/{blogId}";
    }


}
