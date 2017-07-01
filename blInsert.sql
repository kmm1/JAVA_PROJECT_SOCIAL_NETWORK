INSERT INTO users (name, email, password, role) VALUES ('kate', 'km@gmail.com', 'smth', 'admin');
INSERT INTO users (name, email, password) VALUES ('vova', 'vg@gmail.com', 'password');
INSERT INTO users (name, email, password) VALUES ('nastya', 'ng@gmail.com', 'pass');
INSERT INTO users (name, email, password) VALUES ('veranika', 'verg@gmail.com', 'pass');
INSERT INTO users (name, email, password) VALUES ('sergei', 'sp@gmail.com', 'pass');

INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 3, 'req');
INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 1, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 4, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (1, 2, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (4, 2, 'fri');

INSERT INTO profiles (gender, work_country, work_city, home_country, home_city, marital_status,
                      year_of_birth, month_of_birth, day_of_birth, user_id)
VALUES ('MALE', 'Belarus', 'Minsk', 'Belarus', 'Minsk', 'SINGLE', 1976, 07, 12, 2);

INSERT INTO profiles (gender, work_country, work_city, home_country, home_city, marital_status,
                      year_of_birth, month_of_birth, day_of_birth, user_id)
VALUES ('FEMALE', 'Belarus', 'Minsk', 'Belarus', 'Minsk', 'SINGLE', 1976, 08, 10, 4);

INSERT INTO categories (name) VALUES ('FINANCE');
INSERT INTO categories (name) VALUES ('SPORT');
INSERT INTO categories (name) VALUES ('DIFFERENT');

INSERT INTO blogs (title, text, user_id) VALUES ('kiteboarding 1', 'Vova Blog content 1', 2);
INSERT INTO blogs (title, text, user_id) VALUES ('kiteboarding 2', 'Vova Blog content 2', 2);
INSERT INTO blogs (title, text, user_id) VALUES ('VerTravele1', 'Ver Blog content 1', 3);

INSERT INTO blogs_categories (blog_id, category_id) VALUES (1, 2);
INSERT INTO blogs_categories (blog_id, category_id) VALUES (2, 2);
INSERT INTO blogs_categories (blog_id, category_id) VALUES (3, 3);

INSERT INTO comments (comment, user_id, blog_id) VALUES ('Nice blog', 4, 1);
INSERT INTO comments (comment, user_id, blog_id) VALUES ('Keep writing', 3, 1);
INSERT INTO comments (comment, user_id, blog_id, parent_id) VALUES ('Thanks', 2, 1, 1);
INSERT INTO comments (comment, user_id, blog_id, parent_id) VALUES ('My pleasure', 2, 1, 2);


INSERT INTO events (id, name, bl.events.holding_date) VALUES (1, 'ice bucket challenge', 'tomorrow');
INSERT INTO flashmobs (type, about_event, event_id) VALUES ('FLASHMOB','info about this event', 1);


INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Вероника, прив', 2, 4);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Вова, привет', 4, 2);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Как дела', 2, 4);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Норм.', 4, 2);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Hello, Nastya.', 4, 3);

