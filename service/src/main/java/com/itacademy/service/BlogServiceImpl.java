package com.itacademy.service;

import com.itacademy.dao.BlogDao;
import com.itacademy.entity.Blog;
import com.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogServiceImpl extends BaseServiceImpl<Blog> implements BlogService {

    private final BlogDao blogDao;

    @Autowired
    public BlogServiceImpl(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @Override
    public Blog findById(Long id) {
        return blogDao.findById(id);
    }

    @Override
    public Long save(Blog blog) {
        return blogDao.save(blog);
    }

    @Override
    public List<Blog> findAllUsersBlogs(Long userId, Integer limit, Integer offset) {
        return blogDao.findAllUsersBlogs(userId, limit, offset);
    }

    @Override
    public void delete(Blog blog) {
        blogDao.delete(blog);
    }

    @Override
    public void update(Blog blog) {
        blogDao.update(blog);
    }

    @Override
    public void addExistingBlogToExistingCategory(Long categoryId, Long blogId) {
        blogDao.addExistingBlogToExistingCategory(categoryId, blogId);
    }

    @Override
    public void deliteExistingBlogFromExistingCategory(Long categoryId, Long blogId) {
        blogDao.deliteExistingBlogFromExistingCategory(categoryId, blogId);
    }

    @Override
    public List<Blog> findAllBlogsByCategory(Long categoryId) {
        return blogDao.findAllBlogsByCategory(categoryId);
    }

    @Override
    public Integer countUserBlogs(Long userId) {
        return blogDao.countUserBlogs(userId);
    }


}