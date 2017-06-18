package com.itacademy.util;

import com.itacademy.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public final class TestDataImporter {

    private static TestDataImporter INSTANCE;

    private TestDataImporter() {
    }

    public static TestDataImporter getInstance() {
        if (INSTANCE == null) {
            synchronized (TestDataImporter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TestDataImporter();
                }
            }
        }
        return INSTANCE;
    }

    public void importTestData(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();

        User kate = saveUser(session, "kate", "km@gmail.com", "smth", "admin");
        User vova = saveUser(session, "vova", "vg@gmail.com", "smth", "user");
        User veranika = saveUser(session, "nastya", "ng@gmail.com", "pass", "user");
        User nastya = saveUser(session, "veranika", "verg@gmail.com", "pass", "user");
        User sergei = saveUser(session, "sergei", "sp@gmail.com", "pass", "user");

        Message firstMess = saveMessage(session, "Вероника, прив", vova, veranika);
        Message secondMes = saveMessage(session, "Вова привет", veranika, vova);
        Message thirdMes = saveMessage(session, "Как дела", vova, veranika);
        Message forthMes = saveMessage(session, "Норм.", veranika, vova);
        Message fifthMes = saveMessage(session, "Вероника, прив", vova, veranika);

        Profile vovaProfile = saveProfileToUser(session, EnumGender.MALE,
                (new Address("Belarus", "Minsk")),
                (new Address("Belarus", "Minsk")),
                EnumMaritalStatus.SINGLE,
                (new Birthday(1976, 07, 12)),
                vova);

        Profile veronikeProfile = saveProfileToUser(session, EnumGender.FEMALE,
                (new Address("Belarus", "Minsk")),
                (new Address("Belarus", "Minsk")),
                EnumMaritalStatus.SINGLE,
                (new Birthday(1976, 8, 10)),
                veranika);

        Category FINANCE = saveCategory(session, EnumCategory.FINANCE);
        Category DIFFERENT = saveCategory(session, EnumCategory.DIFFERENT);
        Category SPORT = saveCategory(session, EnumCategory.SPORT);


        session.close();

    }

    private User saveUser(Session session, String name, String email, String password, String role) {
        User user = new User(name, email, password, role);
        session.save(user);
        return user;
    }

    private Message saveMessage(Session session, String text, User userSender, User userReceiver) {
        Message message = new Message(text, userSender, userReceiver);
        session.save(message);
        return message;
    }

    private Profile saveProfileToUser(Session session, EnumGender gender,
                                      Address homeAddress, Address workAddress,
                                      EnumMaritalStatus maritalStatus,
                                      Birthday birthday, User user) {
        Profile profile = new Profile(gender, homeAddress, workAddress, maritalStatus, birthday, user);
        session.save(profile);
        return profile;
    }

    private Category saveCategory(Session session, EnumCategory enumCategory) {
        Category category = new Category(enumCategory);
        session.save(category);
        return category;
    }

    //TODO kak prinyat na vxod category
//    private Blog saveBlogToCategoryUser(Session session, String title, String text, User user) {
//        Blog blog = new Blog(title, text, user );
//        session.save(blog);
//        return blog;
//        INSERT INTO blogs (title, text, user_id) VALUES ('kiteboarding 1', 'Vova Blog content 1', 2);
//        INSERT INTO blogs (title, text, user_id) VALUES ('kiteboarding 2', 'Vova Blog content 2', 2);
//        INSERT INTO blogs (title, text, user_id) VALUES ('VerTravele1', 'Ver Blog content 1', 3);
//    }


}

