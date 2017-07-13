package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.Category;
import com.itacademy.entity.EnumCategory;
import com.itacademy.entity.QCategory;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {


    /**
     * Найти названия даты всех блогов в выбранной категории по ее id
     */
    @Override
    public List<Category> findAllCategoriesByBlogId(Long blogId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select c " +
                        "from Blog b join b.categories c " +
                        "where b.id=:blogId", Category.class)
                .setParameter("blogId", blogId)
                .setHint("org.hibernate.cacheable", true)
                .getResultList();
    }

    public List<Category> findOneCategoryByEnumCategory(EnumCategory name) {
        QCategory category = new QCategory("myCategory");
        JPAQuery<Category> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select(category)
                .from(category)
                .where(category.enumCategory.eq(name))
                .setHint("org.hibernate.cacheable", true);
        return query.fetchResults().getResults();
    }
}
