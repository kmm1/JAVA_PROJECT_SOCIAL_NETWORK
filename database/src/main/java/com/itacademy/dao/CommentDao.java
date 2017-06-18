package com.itacademy.dao;

import com.itacademy.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Tom on 10.06.2017.
 */
public class CommentDao extends BaseDao<Comment> {
    public CommentDao() {
        super(Comment.class);
    }

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    /**
     * Написать коментарий к блогу
     */
    public static Long writeCommentToBlog(
            Long userId, Long blogId, String title, String myComment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Blog blog = session.get(Blog.class, blogId);
        User user = session.get(User.class, userId);
        Comment comment = new Comment();
        comment.setBlog(blog);
        comment.setUser(user);
        comment.setComment(myComment);
        Long commentId = (Long) session.save(comment);
        transaction.commit();
        session.close();
        sessionFactory.close();
        return commentId;
    }

    /**
     * Написать коментарий к написанному коментарию
     */
    public static Long writeCommentToExistingComment(Long userId, Long blogId, Long commentParentId,
                                                     String title, String myComment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Blog blog = session.get(Blog.class, blogId);
        User user = session.get(User.class, userId);
        Comment secondComment = new Comment();
        secondComment.setBlog(blog);
        secondComment.setUser(user);
        secondComment.setComment(myComment);
        secondComment.setParentId(commentParentId);
        Long commentId = (Long) session.save(secondComment);
        transaction.commit();
        session.close();
        sessionFactory.close();
        return commentId;
    }

    /**
     * Найти все комментарии к блогу
     */
    public List<Comment> findAllCommentsByBlogId(Long blogId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        QComment comment = new QComment("myComment");
        JPAQuery<Comment> query = new JPAQuery<>();
        query.select(comment.comment, comment.creationDate, comment.user.name)
                .from(comment)
                .join(comment.user)
                .join(comment.blog)
                .where(comment.blog.id.eq(blogId).and(comment.parentId.isNull()))
                .orderBy(comment.blog.creationDate.asc());
        transaction.commit();
        session.close();
        sessionFactory.close();
        return query.fetchResults().getResults();
    }

    //TODO vlojennost komentariev

}
