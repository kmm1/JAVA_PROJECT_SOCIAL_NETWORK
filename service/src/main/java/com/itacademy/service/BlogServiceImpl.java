package com.itacademy.service;

import com.itacademy.dao.BlogDao;
import com.itacademy.entity.Blog;
import com.itacademy.entity.Friend;
import com.itacademy.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

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
    public List<Blog> findAllUsersBlogs(Long userId) {
        return blogDao.findAllUsersBlogs(userId);
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


}