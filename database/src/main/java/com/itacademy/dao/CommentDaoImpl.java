package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.Blog;
import com.itacademy.entity.Comment;
import com.itacademy.entity.QComment;
import com.itacademy.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {
    /**
     * Написать коментарий к блогу
     */
    @Override
    public Long writeCommentToBlog(Long userId, Long blogId, String myComment) {
        Blog blog = getSessionFactory().getCurrentSession().get(Blog.class, blogId);
        User user = getSessionFactory().getCurrentSession().get(User.class, userId);
        Comment comment = new Comment();
        comment.setBlog(blog);
        comment.setUser(user);
        comment.setComment(myComment);
        Long commentId = (Long) getSessionFactory().getCurrentSession().save(comment);
        return commentId;
    }

    /**
     * Написать коментарий к написанному коментарию
     */
    @Override
    public Long writeCommentToExistingComment(Long userId, Long blogId,
                                              Long commentParentId, String myComment) {
        Blog blog = getSessionFactory().getCurrentSession().get(Blog.class, blogId);
        User user = getSessionFactory().getCurrentSession().get(User.class, userId);
        Comment secondComment = new Comment();
        secondComment.setBlog(blog);
        secondComment.setUser(user);
        secondComment.setComment(myComment);
        secondComment.setParentId(commentParentId);
        Long commentId = (Long) getSessionFactory().getCurrentSession().save(secondComment);
        return commentId;
    }

    /**
     * Найти все комментарии к блогу
     */
    @Override
    public List<Comment> findAllCommentsByBlogId(Long blogId) {
        QComment comment = new QComment("myComment");
        JPAQuery<Comment> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(comment/*.comment, comment.creationDate, comment.user.name*/)
                .from(comment)
                .join(comment.user)
                .join(comment.blog)
                .where(comment.blog.id.eq(blogId).and(comment.parentId.isNull()))
                .orderBy(comment.blog.creationDate.asc());
        return query.fetchResults().getResults();
    }

    //TODO vlojennost komentariev

}
