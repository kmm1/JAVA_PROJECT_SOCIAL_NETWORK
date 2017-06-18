package com.itacademy.dao;

import com.itacademy.entity.*;
import com.itacademy.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.junit.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Tom on 14.06.2017.
 */
public class CategoryDaoTest {

    private SessionFactory sessionFactory;
    CategoryDao categoryDao = new CategoryDao();


    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void saveCategory() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        categoryDao.saveOne(category);
        assertEquals(category.getEnumCategory().toString(), "DIFFERENT");
        categoryDao.deleteOneById(1L);
    }

    @Test
    public void getCategoryById() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        categoryDao.saveOne(category);
        Category category1 = categoryDao.findOneById(1L);
        assertThat(category1, notNullValue());
        categoryDao.deleteOneById(1L);
    }

    @Test
    public void deleteCategoryById() {
        Category category = new Category();
        category.setEnumCategory(EnumCategory.DIFFERENT);
        categoryDao.saveOne(category);
        categoryDao.deleteOneById(1L);
        Category category1 = categoryDao.findOneById(1L);
        assertThat(category1, nullValue());
    }

    @Test
    public void testFindAll() {
        Category category1 = new Category();
        Category category2 = new Category();
        categoryDao.saveOne(category1);
        categoryDao.saveOne(category2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Category> results = categoryDao.findAllCategories(session);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(results);
        session.getTransaction().commit();
        session.close();
    }


    @After
    public void finish() {
        sessionFactory.close();
    }
}

