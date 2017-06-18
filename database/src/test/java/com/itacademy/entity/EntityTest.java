package com.itacademy.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.junit.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.itacademy.entity.EnumCategory.BUSINESS;
import static com.itacademy.entity.QAddress.address;
import static org.junit.Assert.*;


public class EntityTest {
    private SessionFactory sessionFactory;


    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        //TODO testimporter ne rabotaet
        // TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void testSaveUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setName("Test");
        user.setEmail("test@gmail.com");
        user.setPassword("test");
        user.setRole("user");
        user.setRegistrationDate(LocalDateTime.now());
        Long id = (Long) session.save(user);
        User savedUser = session.find(User.class, id);
        assertEquals(savedUser.getName(), "Test");
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testSaveProfileToUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, 1L);
        Profile profile = new Profile();
        profile.setGender(EnumGender.FEMALE);
        profile.setHomeAddress(new Address("USA", "New York"));
        profile.setWorkAddress(new Address("USA", "New York"));
        profile.setMaritalStatus(EnumMaritalStatus.SINGLE);
        profile.setBirthday(new Birthday(1990, 01, 01));
        profile.setUser(user);
        session.save(profile);
        assertEquals(profile.getGender().toString(), "FEMALE");
        assertEquals(profile.getHomeAddress().getCountry(), "USA");
        assertEquals(profile.getHomeAddress().getCity(), "New York");
        assertEquals(profile.getWorkAddress().getCountry(), "USA");
        assertEquals(profile.getMaritalStatus().toString(), "SINGLE");
        assertEquals(profile.getBirthday().getYearOfBirth(), 1990);
        assertEquals(profile.getBirthday().getMonthOfBirth(), 01);
        assertEquals(profile.getBirthday().getMonthOfBirth(), 01);
        session.getTransaction().commit();
        session.close();
    }



    @Test
    public void testSaveNewCommentToBlogToUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Comment comment = new Comment();
        User user = session.get(User.class, 1L);
        Blog blog = session.get(Blog.class, 1L);
        comment.setComment("my first comment");
        comment.setCreationDate(LocalDateTime.now());
        comment.setBlog(blog);
        comment.setUser(user);
        session.save(comment);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testSaveCommentToExistingCommentBlogUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Comment comment = new Comment();
        Comment anotherNewComment = new Comment();
        Long id = comment.getId();
        User user = session.get(User.class, 1L);
        Blog blog = session.get(Blog.class, 1L);
        anotherNewComment.setParentId(id);
        anotherNewComment.setComment("my comment to previous comment");
        anotherNewComment.setCreationDate(LocalDateTime.now());
        anotherNewComment.setBlog(blog);
        anotherNewComment.setUser(user);
        session.save(anotherNewComment);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testSaveCategory() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Category category = new Category();
        category.setEnumCategory(BUSINESS);
        Long id = (Long) session.save(category);
        Category savedCategory = session.find(Category.class, id);
        assertEquals(savedCategory.getEnumCategory().toString(), "BUSINESS");
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testSaveFriendRequest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Friend friend = new Friend();
        User userSender = session.get(User.class, 1L);
        User userReceiver = session.get(User.class, 2L);
        friend.setUserSender(userSender);
        friend.setUserReceiver(userReceiver);
        friend.setStatus("req");
        session.save(friend);
        assertEquals(friend.getStatus(), "req");
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testSaveMessage() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Message message = new Message();
        User userSender = session.get(User.class, 1L);
        User userReceiver = session.get(User.class, 2L);
        message.setCreationDate(LocalDateTime.now());
        message.setText("Hi! This is my first message to you.");
        message.setUserReceiver(userReceiver);
        message.setUserSender(userSender);
        session.save(message);
        assertEquals(message.getText(), "Hi! This is my first message to you.");
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testSaveFlashmob() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Flashmob flashmob = new Flashmob();
        flashmob.setName("ice bucket challenge");
        flashmob.setFlashmobType(EnumFlashmobType.FLASHMOB);
        session.save(flashmob);
        assertEquals(flashmob.getFlashmobType().toString(), "FLASHMOB");
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testSaveBlogToCategory() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Category category = new Category();
        category.setEnumCategory(BUSINESS);
        Long id = (Long) session.save(category);
        Blog blog = new Blog();
        blog.getCategories().add(category);
        session.save(blog);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testSaveBlogToUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        Long id = (Long) session.save(user);
        Blog blog = new Blog("test title", "test text");
        session.save(blog);
        blog.getUser();
        session.getTransaction().commit();
        session.close();
    }


    @Test
    public void testGetEventsFlashmobs() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Flashmob flashmob = new Flashmob();
        flashmob.setName("ice bucket challenge");
        session.save(flashmob);
        List<Event> events = session
                .createQuery("from Event", Event.class)
                .getResultList();
        events.forEach(System.out::println);
        List<Flashmob> flashmobs = session
                .createQuery("from Flashmob", Flashmob.class)
                .getResultList();
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        flashmobs.forEach(System.out::println);
        events.forEach(System.out::println);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        session.getTransaction().commit();
        session.close();
    }


    @After
    public void finish() {
        sessionFactory.close();
    }
}