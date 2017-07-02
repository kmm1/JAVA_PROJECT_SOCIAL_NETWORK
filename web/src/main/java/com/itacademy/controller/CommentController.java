package com.itacademy.controller;

import com.itacademy.entity.*;
import com.itacademy.service.BlogService;
import com.itacademy.service.CommentService;
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
public class CommentController {

    private final BlogService blogService;
    private final UserService userService;
    private final CommentService commentService;


    @Autowired
    public CommentController(BlogService blogService,
                             UserService userService, CommentService commentService) {
        this.blogService = blogService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @ModelAttribute("blog")
    public Blog blog() {
        return new Blog();
    }

    @ModelAttribute("comment")
    public Comment comment() {
        return new Comment();
    }


    @GetMapping(path = "/saveComment/{blogId}")
    public String readBlog(@PathVariable("blogId") Long blogId,
                           @RequestParam String text,
                           Model model, HttpServletRequest req) {
        String userName = (String) req.getSession().getAttribute("userName");
        Long userId = (Long) req.getSession().getAttribute("userId");
        User myUser = userService.findOneUserByName(userName);
        Blog myBlog = blogService.findById(blogId);
        Comment comment = new Comment();
        comment.setBlog(myBlog);
        comment.setUser(myUser);
        comment.setComment(text);
        commentService.save(comment);

        List<Comment> allCommentsByBlogId = commentService.findAllCommentsByBlogId(blogId);
        model.addAttribute("allCommentsByBlogId", allCommentsByBlogId);
        model.addAttribute("myBlog", myBlog);
        model.addAttribute("userName", userName);
        System.out.println(blogId);
        System.out.println(myBlog);
        System.out.println("KKKKKKKKKKKKK" + allCommentsByBlogId);
        return "blog-read";
    }

    @GetMapping(path = "/deliteComment/{commentId}")
    public String deliteComment(@PathVariable("commentId") Long commentId,
                                Model model, HttpServletRequest req) {
        String userName = (String) req.getSession().getAttribute("userName");
        Long userId = (Long) req.getSession().getAttribute("userId");
        User myUser = userService.findOneUserByName(userName);
        Comment myComment = commentService.findById(commentId);
        Blog myBlog = myComment.getBlog();
        Long blogId = myBlog.getId();
        if (myComment.getParentId() != null) {
//            myComment.setComment("Коментарий удален");
//            commentService.update(myComment);
//            List<Comment> allCommentsByBlogId = commentService.findAllCommentsByBlogId(blogId);
//            model.addAttribute("allCommentsByBlogId", allCommentsByBlogId);
//            model.addAttribute("myBlog", myBlog);
//            model.addAttribute("userName", userName);
//            return "blog-read";
        }
        commentService.delete(myComment);
        List<Comment> allCommentsByBlogId = commentService.findAllCommentsByBlogId(blogId);
        model.addAttribute("allCommentsByBlogId", allCommentsByBlogId);
        model.addAttribute("myBlog", myBlog);
        model.addAttribute("userName", userName);
        return "blog-read";
    }


}
