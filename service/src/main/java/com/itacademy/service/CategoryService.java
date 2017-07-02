package com.itacademy.service;


import com.itacademy.entity.Blog;
import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
import com.itacademy.entity.Flashmob;

import java.util.List;

public interface CategoryService {

    Category findById(Long id);

    Long save(Category category);

    void delete(Category category);

    void update(Category category);

    List<Category> findAll();

    List<Category> findAllCategoriesByBlogId(Long blogId);

    List<Category> findOneCategoryByEnumCategory(EnumCategory name);


}
