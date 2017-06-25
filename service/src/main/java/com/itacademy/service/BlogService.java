package com.itacademy.service;


import com.itacademy.entity.Blog;

public interface BlogService {
    Blog findById(Long id);

    Long save(Blog blog);
}
