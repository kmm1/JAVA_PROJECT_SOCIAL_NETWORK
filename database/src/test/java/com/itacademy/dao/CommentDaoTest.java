package com.itacademy.dao;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Comment;
import com.itacademy.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class CommentDaoTest extends BaseTest {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BlogDao blogDao;


    @Before
    public void init() {
        // ....
    }

    @Test
    public void testSaveComment() {
        User user = new User();
        userDao.save(user);
        Blog blog = new Blog();
        blogDao.save(blog);
        Comment comment = new Comment();
        comment.setComment("My first Comment");
        comment.setCreationDate(LocalDateTime.now());
        comment.setUser(user);
        comment.setBlog(blog);
        commentDao.save(comment);
        assertThat(comment.getCreationDate(), notNullValue());
        assertEquals(comment.getBlog(), blog);
        assertEquals(comment.getUser(), user);
        assertEquals(comment.getComment(), "My first Comment");
    }

    @Test
    public void testGetCommentById() {
        Comment comment = new Comment();
        Long commentId = commentDao.save(comment);
        Comment comment1 = commentDao.findById(commentId);
        assertThat(comment1, notNullValue());
    }

    @Test
    public void testDeleteOne() {
        Comment comment = new Comment();
        Long commentId = commentDao.save(comment);
        assertThat(comment, notNullValue());
        commentDao.delete(comment);
        Comment comment1 = commentDao.findById(commentId);
        assertThat(comment1, nullValue());
    }

    @Test
    public void testWriteCommentToBlog() {
        User user = new User();
        Long userId = (Long) userDao.save(user);
        Blog blog = new Blog();
        blog.setUser(user);
        Long blogId = (Long) blogDao.save(blog);
        Comment comment2 = new Comment();
        Long commentId = commentDao.writeCommentToBlog(userId, blogId,
                "test");
        Comment comment = commentDao.findById(commentId);
        assertEquals(comment.getComment(), "test");
    }


    @Test
    public void testWriteCommentToExistingComment() {
        User user = new User();
        Long userId = userDao.save(user);
        Blog blog = new Blog();
        blog.setUser(user);
        Long blogId = blogDao.save(blog);
        Comment comment1 = new Comment();
        comment1.setBlog(blog);
        comment1.setUser(user);
        Long commentParentId = commentDao.save(comment1);
        Long commentId = commentDao.writeCommentToExistingComment(
                userId, blogId, commentParentId, "test");
        Comment comment = commentDao.findById(commentId);
        assertEquals(comment.getComment(), "test");
    }


    @After
    public void finish() {
        // ...
    }
}
