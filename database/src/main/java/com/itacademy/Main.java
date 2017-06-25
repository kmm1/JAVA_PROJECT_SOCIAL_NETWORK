package com.itacademy;

import com.itacademy.dao.*;
import com.itacademy.entity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.*;
import java.util.*;

import static com.itacademy.entity.EnumCategory.BUSINESS;

public class Main {
    public static void main(String[] args) {
        // SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();

//BlogDaoImpl.addBlogToCategory(1L, 3L);
//BlogDaoImpl.addNewBlogToExistingCategory(1L, 1L, "kkk", "cont");
//        findAllFriendsByUserId
//            FriendDaoImpl friendDao = new FriendDaoImpl();
//            List<Friend> results = friendDao.findAllFriendsByUserId(session, "vova", "fri");
//            System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
//            System.out.println(results);
//            session.getTransaction().commit();
//            session.close();
//FriendDaoImpl.sendFriendRequest(session, 3L, 5L);
//        session.getTransaction().commit();
//            session.close();

//        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
//        Category category = categoryDao.findOneById(1L);
//        BlogDaoImpl blogDao = new BlogDaoImpl();
//        Blog blog = blogDao.findOneById(1L);
//        category.getBlogs().add(blog);
//        blog.getCategories().add(category);
//        session.save(blog);
//        session.getTransaction().commit();
//        session.close();

//        Category category = new Category();
//        category.setEnumCategory(BUSINESS);
//        Long id = (Long) session.save(category);
//        BlogDaoImpl blogDao = new BlogDaoImpl();
//        Blog blog = blogDao.findOneById(1L);
//        blog.getCategories().add(category);
//        session.save(blog);
//        session.getTransaction().commit();
//        session.close();

        // ChatRoom
//            MessageDaoImpl messageDao = new MessageDaoImpl();
//            List<Message> results = messageDao.chatByTwoUsers(session, 2L, 4L);
//            System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
//            System.out.println(results);
//            session.getTransaction().commit();
//            session.close();


//RABOCHII KOD!!!!!!!!!!!!!!!!!
//        Blog blog = session.get(Blog.class, 1L);
//        Category category = new Category();
//        blog.getCategories().add(category);
//        session.save(category);
//        transaction.commit();
//        session.close();

//        Blog blog = session.get(Blog.class, 2L);
//        Category category = session.get(Category.class, 1L);
//        blog.getCategories().add(category);
//        session.save(category);
//        transaction.commit();
//        session.close();


//   CategoryDaoImpl.addBlogToCategory(session, 1L, 5L);
//        session.getTransaction().commit();
//        session.close();

//
//        FriendDaoImpl.acceptFriendRequest(session, 9L);
//        session.getTransaction().commit();
//        session.close();

        //     naiti vsex user
//            UserDaoImpl userDao = new UserDaoImpl();
//            List<User> results = userDao.findAll(session);
//            System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
//            System.out.println(results);
//            session.getTransaction().commit();
//            session.close();


        //1
//        User employee = new User();
//        employee.setName("Igor Sukach");
//        session.saveOrUpdate(employee);
        //2
//        User user = session.get(User.class, 1L);
//        Profile profile = new Profile();
//        profile.setGender(EnumGender.MALE);
//        profile.setUser(user);
//        session.save(profile);
//        transaction.commit();
//        session.close();
        //3
//        User user = session.get(User.class, 1L);
//        Blog blog = new Blog();
//        blog.setTitle("Learning About Blogging and How to Blog");
//        blog.setText("Blogs are now carrying breaking news. And so on.");
//        blog.setCreationDate(LocalDateTime.now());
//        blog.setUser(user);
//        session.save(blog);
//        transaction.commit();
//        session.close();
        //4
//        Comment comment = new Comment();
//        User user = session.get(User.class, 1L);
//        Blog blog = session.get(Blog.class, 1L);
//        comment.setName("my first comment");
//        comment.setCreationDate(LocalDateTime.now());
//        comment.setBlog(blog);
//        comment.setUser(user);
//        session.save(comment);
//        transaction.commit();
//        session.close();


       /* List<Employee> resultList
                = session.createQuery("from Employee", Employee.class).getResultList();
        resultList.forEach(System.out::println);*/
        // sessionFactory.close();
    }
}
