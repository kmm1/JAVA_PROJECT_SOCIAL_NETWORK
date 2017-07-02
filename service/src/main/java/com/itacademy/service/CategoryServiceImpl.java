package com.itacademy.service;

import com.itacademy.dao.CategoryDao;
import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public Long save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> findAllCategoriesByBlogId(Long blogId) {
        return categoryDao.findAllCategoriesByBlogId(blogId);
    }

    @Override
    public List<Category> findOneCategoryByEnumCategory(EnumCategory name) {
        return categoryDao.findOneCategoryByEnumCategory(name);
    }

}