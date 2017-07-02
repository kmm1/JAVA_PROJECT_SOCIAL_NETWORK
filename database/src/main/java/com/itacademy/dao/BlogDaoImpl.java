package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.Blog;
import com.itacademy.entity.Category;
import com.itacademy.entity.QBlog;
import com.itacademy.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogDaoImpl extends BaseDaoImpl<Blog> implements BlogDao {


    /**
     * Пользователь создает новый блог без категории
     */
    @Override
    public Long createBlog(Long categoryId, Long userId, String title, String content) {
        User user = getSessionFactory().getCurrentSession().get(User.class, userId);
        Blog blog = new Blog();
        blog.setUser(user);
        blog.setTitle(title);
        blog.setText(content);
        Long blogId = (Long) getSessionFactory().getCurrentSession().save(blog);
        return blogId;
    }


    /**
     * Пользователь к существующему блогу добавляет категорию
     */
    @Override
    public void addExistingBlogToExistingCategory(Long categoryId, Long blogId) {
        Blog blog = getSessionFactory().getCurrentSession().get(Blog.class, blogId);
        Category category = getSessionFactory().getCurrentSession().get(Category.class, categoryId);
        blog.getCategories().add(category);
    }

    @Override
    public void deliteExistingBlogFromExistingCategory(Long categoryId, Long blogId) {
        Blog blog = getSessionFactory().getCurrentSession().get(Blog.class, blogId);
        Category category = getSessionFactory().getCurrentSession().get(Category.class, categoryId);
        blog.getCategories().remove(category);
    }


    /**
     * Достать все блоги у пользователя
     * SELECT b.title FROM blogs AS b LEFT JOIN users AS u ON user_id = u.id
     * WHERE u.id = 2 ORDER BY (b.creation_date);
     */
    @Override
    public List<Blog> findAllUsersBlogs(Long userId) {
        QBlog blog = new QBlog("myBlog");
        JPAQuery<Blog> query = new JPAQuery<>(getSessionFactory().getCurrentSession());
        query.select (blog)
                .from(blog)
                .join(blog.user)
                .where(blog.user.id.eq(userId))
                .orderBy(blog.creationDate.asc());
        return query.fetchResults().getResults();
    }

    @Override
    public List<Blog> findAllBlogsByCategory(Long categoryId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select b.title, b.creationDate " +
                        "from Blog b join b.categories c " +
                        "where c.id=:categoryId", Blog.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }


}