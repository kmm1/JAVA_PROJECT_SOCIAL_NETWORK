package com.itacademy.dao;

import com.itacademy.entity.Blog;
import com.itacademy.entity.Comment;
import com.itacademy.entity.Friend;
import com.itacademy.entity.User;
import com.itacademy.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class CommentDaoTest {

    private SessionFactory sessionFactory;
    private UserDao userDao = new UserDao();
    private BlogDao blogDao = new BlogDao();
    private CommentDao commentDao = new CommentDao();
    Comment comment = new Comment();

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void testSaveOne() {
        comment.setComment("My first Comment");
        commentDao.saveOne(comment);
        assertThat(comment, notNullValue());
        System.out.println(comment);
        commentDao.deleteOneById(1L);
    }

    @Test
    public void testGetOne() {
        comment.setComment("My first Comment");
        commentDao.saveOne(comment);
        Comment comment1 = commentDao.findOneById(1L);
        assertThat(comment1, notNullValue());
        System.out.println(comment1);
        commentDao.deleteOneById(1L);
    }

    @Test
    public void testDeleteOne() {
        comment.setComment("My first Comment");
        commentDao.saveOne(comment);
        commentDao.deleteOneById(1L);
        Comment comment1 = commentDao.findOneById(1L);
        assertThat(comment1, nullValue());
    }

    @Test
    public void testWriteCommentToBlog() {
        User user = new User();
        Long userId = (Long) userDao.saveOne(user);
        Blog blog = new Blog();
        blog.setUser(user);
        Long blogId = (Long) blogDao.saveOne(blog);
        Comment comment2 = new Comment();
        Long commentId = commentDao.writeCommentToBlog(userId, blogId,
                "test");
        comment = commentDao.findOneById(commentId);
        assertEquals(comment.getComment(), "test");
    }


    @Test
    public void testWriteCommentToExistingComment() {
        User user = new User();
        Long userId = (Long) userDao.saveOne(user);
        Blog blog = new Blog();
        blog.setUser(user);
        Long blogId = (Long) blogDao.saveOne(blog);
        Comment comment1 = new Comment();
        comment1.setBlog(blog);
        comment1.setUser(user);
        Long commentParentId = (Long) commentDao.saveOne(comment1);
        Comment comment2 = new Comment();
        Long commentId = commentDao.writeCommentToExistingComment(userId, blogId, commentParentId,
                "test");
        comment = commentDao.findOneById(commentId);
        assertEquals(comment.getComment(), "test");
    }


    @After
    public void finish() {
        sessionFactory.close();
    }
}
