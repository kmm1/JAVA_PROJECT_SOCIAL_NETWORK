package com.itacademy.service;


import com.itacademy.entity.Blog;

import java.util.List;

public interface BlogService {

    Blog findById(Long id);

    Long save(Blog blog);

    List<Blog> findAllUsersBlogs(Long userId);

    void delete(Blog blog);

    void update(Blog blog);

    void addExistingBlogToExistingCategory(Long categoryId, Long blogId);


}
