package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.Comment;

import java.util.List;


public interface CommentDao extends BaseDao<Comment> {
    Long writeCommentToBlog(Long userId, Long blogId, String myComment);

    Long writeCommentToExistingComment(Long userId, Long blogId,
                                       Long commentParentId, String myComment);

    List<Comment> findAllCommentsByBlogId(Long blogId);
}