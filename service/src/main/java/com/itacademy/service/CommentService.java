package com.itacademy.service;


import com.itacademy.entity.Comment;
import com.itacademy.service.common.BaseService;

import java.util.List;

public interface CommentService extends BaseService<Comment> {

    Comment findById(Long id);

    Long save(Comment comment);

    void update(Comment comment);

    void delete(Comment comment);

    List<Comment> findAll();

    Long writeCommentToBlog(Long userId, Long blogId, String myComment);

    Long writeCommentToExistingComment(Long userId, Long blogId,
                                       Long commentParentId, String myComment);

    List<Comment> findAllCommentsByBlogId(Long blogId);
}
