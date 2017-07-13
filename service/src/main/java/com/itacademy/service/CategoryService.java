package com.itacademy.service;


import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
import com.itacademy.service.common.BaseService;

import java.util.List;

public interface CategoryService extends BaseService<Category> {

    Category findById(Long id);

    Long save(Category category);

    void delete(Category category);

    void update(Category category);

    List<Category> findAll();

    List<Category> findAllCategoriesByBlogId(Long blogId);

    List<Category> findOneCategoryByEnumCategory(EnumCategory name);


}
