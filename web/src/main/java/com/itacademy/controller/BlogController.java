package com.itacademy.controller;

import com.itacademy.entity.*;
import com.itacademy.service.BlogService;
import com.itacademy.service.CategoryService;
import com.itacademy.service.CommentService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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

    @ModelAttribute("roles")
    public Collection<? extends GrantedAuthority> roles() {
        Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        return roles;
    }

    @ModelAttribute("blog")
    public Blog blog() {
        return new Blog();
    }

    @ModelAttribute("user")
    public SystemUser user() {
        return new SystemUser();
    }

    @ModelAttribute("allCategories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    public Integer limit = 5;


    @GetMapping(path = "/blog")
    public String showMyBlogs(Blog blog, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        Long userId = userService.findOneUserByName(userName).getId();

        Integer numberOfBlogs = blogService.countUserBlogs(userId);
        Integer numberOfPages = (int) Math.ceil((double) numberOfBlogs / (double) limit);
        List<Object> numbers = new ArrayList<>();
        for (int i = 1; i < numberOfPages + 1; i++) {
            numbers.add(i);
        }
        List test = blogService.findAllUsersBlogs(userId, 1, 0);
        if (test.size() == 0) {
            return "blog-form";
        }
        ArrayList<Blog> findAllBlogsByUserName = (ArrayList<Blog>) blogService.findAllUsersBlogs(userId, limit, 0);
        model.addAttribute("numbers", numbers);
        model.addAttribute("findAllBlogsByUserName", findAllBlogsByUserName);
        return "blog";
    }

    @GetMapping(path = "/blog/{pageId}")
    public String paging(@PathVariable("pageId") Integer pageId, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        Long userId = userService.findOneUserByName(userName).getId();

        Integer numberOfBlogs = blogService.countUserBlogs(userId);
        Integer numberOfPages = (int) Math.ceil((double) numberOfBlogs / (double) limit);
        List<Object> numbers = new ArrayList<>();
        for (int i = 1; i < numberOfPages + 1; i++) {
            numbers.add(i);
        }
        List test = blogService.findAllUsersBlogs(userId, 1, 0);
        if (test.size() == 0) {
            return "blog-form";
        }
        Integer offset = (pageId - 1) * limit;
        ArrayList<Blog> findAllBlogsByUserName = (ArrayList<Blog>) blogService.findAllUsersBlogs(userId, limit, offset);
        model.addAttribute("numbers", numbers);
        model.addAttribute("findAllBlogsByUserName", findAllBlogsByUserName);
        return "blog";
    }


    @GetMapping(path = "/readBlog/{blogId}")
    public String readBlog(@PathVariable("blogId") Long blogId, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
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
    public String changeBlog2(Blog blog, @PathVariable("blogId") Long blogId, Model model,
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
    public String createBlog(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        Long userId = userService.findOneUserByName(userName).getId();
        SystemUser myUser = userService.findOneUserByName(userName);
        model.addAttribute("myUser", myUser);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(myUser);
        return "blog-form";
    }

    @PostMapping(path = "/saveBlog")
    public String createBlog2(Blog blog, ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        SystemUser myUser = userService.findOneUserByName(userName);
        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGG");
        System.out.println(myUser);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
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

    @GetMapping(path = "/readBlogInCategory/{categoryId}")
    public String readBlogInCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
        List<Blog> allBlogsInCategory = blogService.findAllBlogsByCategory(categoryId);
        model.addAttribute("allBlogsInCategory", allBlogsInCategory);
        return "blog-in-category";
//        return "blog";
    }


}
