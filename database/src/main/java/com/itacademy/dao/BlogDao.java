package com.itacademy.dao;

import com.itacademy.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Tom on 10.06.2017.
 */
public class BlogDao extends BaseDao<Blog> {
    public BlogDao() {
        super(Blog.class);
    }

    private static SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    /**
     * Пользователь создает новый блог без категории
     */
    public static Long createBlog(Long categoryId, Long userId, String title, String content) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        Blog blog = new Blog();
        blog.setUser(user);
        blog.setTitle(title);
        blog.setText(content);
        Long blogId = (Long) session.save(blog);
        transaction.commit();
        session.close();
        sessionFactory.close();
        return blogId;
    }

    /**
     * Пользователь к существующему блогу добавляет категорию
     */
    public static void addExistingBlogToExistingCategory(Long categoryId, Long userId, Long blogId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Blog blog = session.get(Blog.class, blogId);
        Category category = session.get(Category.class, categoryId);
        blog.getCategories().add(category);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    /**
     * Достать все блоги у пользователя
     * SELECT b.title FROM blogs AS b LEFT JOIN users AS u ON user_id = u.id
     * WHERE u.id = 2 ORDER BY (b.creation_date);
     */
    public List<Blog> findAll(Long userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        QBlog blog = new QBlog("myBlog");
        JPAQuery<Blog> query = new JPAQuery<>();
        query.select(blog.title)
                .from(blog)
                .join(blog.user)
                .where(blog.user.id.eq(userId))
                .orderBy(blog.creationDate.asc());
        transaction.commit();
        session.close();
        sessionFactory.close();
        return query.fetchResults().getResults();
    }

    /**
     * Найти названия даты всех блогов в выбранной категории по ее id
     */
    public List<Blog> findAllBlogsByCategory(Long categoryId, Session session) {
        return session
                .createQuery("select b.title, b.creationDate " +
                        "from Blog b join b.categories c " +
                        "where c.id=:categoryId", Blog.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

}