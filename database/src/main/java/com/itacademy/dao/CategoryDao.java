package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;

import java.util.List;


public interface CategoryDao extends BaseDao<Category> {

    List<Category> findAllCategoriesByBlogId(Long blogId);

    List<Category> findOneCategoryByEnumCategory(EnumCategory name);
}
