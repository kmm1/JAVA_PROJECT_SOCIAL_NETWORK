package com.itacademy.service;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Comment;
import com.itacademy.entity.SystemUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class CommentServiceTest extends BaseTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;


    @Test
    public void testSaveComment() {
        SystemUser user = new SystemUser();
        userService.save(user);
        Blog blog = new Blog();
        blogService.save(blog);
        Comment comment = new Comment();
        comment.setComment("My first Comment");
        comment.setCreationDate(LocalDateTime.now());
        comment.setUser(user);
        comment.setBlog(blog);
        commentService.save(comment);
        assertThat(comment.getCreationDate(), notNullValue());
        assertEquals(comment.getBlog(), blog);
        assertEquals(comment.getUser(), user);
        assertEquals(comment.getComment(), "My first Comment");
    }

    @Test
    public void testUpdateComment() {
        Comment comment = new Comment();
        comment.setComment("My first Comment");
        Long commentId = commentService.save(comment);
        Comment comment2 = commentService.findById(commentId);
        comment2.setComment("My second comment");
        commentService.update(comment2);
        Comment comment3 = commentService.findById(commentId);
        assertEquals(comment3.getComment(), "My second comment");
    }

    @Test
    public void testGetCommentById() {
        Comment comment = new Comment();
        Long commentId = commentService.save(comment);
        Comment comment1 = commentService.findById(commentId);
        assertThat(comment1, notNullValue());
    }

    @Test
    public void testFindAllComments() {
        Comment comment = new Comment();
        Comment comment2 = new Comment();
        comment.setComment("My second Comment");
        comment2.setComment("My first Comment");
        commentService.save(comment);
        commentService.save(comment2);
        List<Comment> result = commentService.findAll();
        assertEquals(result.size(), 2);
    }

    @Test
    public void testDeleteOne() {
        Comment comment = new Comment();
        Long commentId = commentService.save(comment);
        assertThat(comment, notNullValue());
        commentService.delete(comment);
        Comment comment1 = commentService.findById(commentId);
        assertThat(comment1, nullValue());
    }

    @Test
    public void testWriteCommentToBlog() {
        SystemUser user = new SystemUser();
        Long userId = userService.save(user);
        Blog blog = new Blog();
        blog.setUser(user);
        Long blogId = blogService.save(blog);
        Comment comment2 = new Comment();
        Long commentId = commentService.writeCommentToBlog(userId, blogId,
                "test");
        Comment comment = commentService.findById(commentId);
        assertEquals(comment.getComment(), "test");
    }

    @Test
    public void testWriteCommentToExistingComment() {
        SystemUser user = new SystemUser();
        Long userId = userService.save(user);
        Blog blog = new Blog();
        blog.setUser(user);
        Long blogId = blogService.save(blog);
        Comment comment1 = new Comment();
        comment1.setBlog(blog);
        comment1.setUser(user);
        Long commentParentId = commentService.save(comment1);
        Long commentId = commentService.writeCommentToExistingComment(
                userId, blogId, commentParentId, "test");
        Comment comment = commentService.findById(commentId);
        assertEquals(comment.getComment(), "test");
    }

}
