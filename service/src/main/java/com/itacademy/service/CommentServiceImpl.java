package com.itacademy.service;

import com.itacademy.dao.CommentDao;
import com.itacademy.entity.Comment;
import com.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public Long save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public Long writeCommentToBlog(Long userId, Long blogId, String myComment) {
        return commentDao.writeCommentToBlog(userId, blogId, myComment);
    }

    @Override
    public Long writeCommentToExistingComment(Long userId, Long blogId, Long commentParentId, String myComment) {
        return commentDao.writeCommentToExistingComment(userId, blogId, commentParentId, myComment);
    }

    @Override
    public List<Comment> findAllCommentsByBlogId(Long blogId) {
        return commentDao.findAllCommentsByBlogId(blogId);
    }
}