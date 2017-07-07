CREATE DATABASE bl;
USE bl;

DROP TABLE flashmobs;
DROP TABLE events;

DROP TABLE friends;
DROP TABLE messages;
DROP TABLE blogs_categories;
DROP TABLE comments;
DROP TABLE blogs;
DROP TABLE categories;
DROP TABLE profiles;
DROP TABLE users_roles;
DROP TABLE users;
DROP TABLE roles;


CREATE TABLE events (
  id            INT AUTO_INCREMENT,
  name          VARCHAR(255),
  holding_date  VARCHAR(255),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  version       INT,
  PRIMARY KEY (id)
);

CREATE TABLE flashmobs (
  type        VARCHAR(255),
  about_event TEXT,
  event_id    INT,
  PRIMARY KEY (event_id),
  FOREIGN KEY (event_id) REFERENCES events (id)
);


CREATE TABLE roles (
  id   INT AUTO_INCREMENT,
  name VARCHAR(15) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id                INT AUTO_INCREMENT,
  name              VARCHAR(20),
  email             VARCHAR(20),
  password          VARCHAR(20),
  registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id INT,
  role_id INT,
  PRIMARY KEY (user_id, role_id)
);


CREATE TABLE blogs (
  id            INT                AUTO_INCREMENT,
  title         VARCHAR(255),
  TEXT          TEXT,
  creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_id       INT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE categories (
  id   INT AUTO_INCREMENT,
  name VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE blogs_categories (
  blog_id     INT,
  category_id INT,
  PRIMARY KEY (blog_id, category_id),
  FOREIGN KEY (blog_id) REFERENCES blogs (id),
  FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE comments (
  id            INT       AUTO_INCREMENT,
  comment       TEXT,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  parent_id     INT       DEFAULT NULL,
  user_id       INT,
  blog_id       INT,
  PRIMARY KEY (id),
  FOREIGN KEY (parent_id) REFERENCES comments (id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (blog_id) REFERENCES blogs (id)
);

CREATE TABLE messages (
  id               INT                AUTO_INCREMENT,
  text             TEXT,
  creation_date    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_sender_id   INT,
  user_receiver_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_sender_id) REFERENCES users (id),
  FOREIGN KEY (user_receiver_id) REFERENCES users (id)
);

CREATE TABLE friends (
  id         INT AUTO_INCREMENT,
  friend_one INT,
  friend_two INT,
  status     VARCHAR(5),
  PRIMARY KEY (id),
  FOREIGN KEY (friend_one) REFERENCES users (id),
  FOREIGN KEY (friend_two) REFERENCES users (id)
);

CREATE TABLE profiles (
  id             INT AUTO_INCREMENT,
  about_me       TEXT,
  gender         VARCHAR(50),
  work_country   VARCHAR(50),
  work_city      VARCHAR(50),
  home_country   VARCHAR(50),
  home_city      VARCHAR(50),
  marital_status VARCHAR(50),
  year_of_birth  INT,
  month_of_birth INT,
  day_of_birth   INT,
  user_id        INT UNIQUE,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);


INSERT INTO events (id, name, holding_date, version) VALUES (1, 'ice bucket challenge', 'tomorrow', 0);
INSERT INTO flashmobs (type, about_event, event_id) VALUES ('FLASHMOB', 'info about this event', 1);
INSERT INTO events (id, name, holding_date, version) VALUES (2, 'raise your hand', 'next week', 0);
INSERT INTO flashmobs (type, about_event, event_id) VALUES ('FLASHMOB', 'info about this event', 2);


INSERT INTO users (name, email, password) VALUES ('kate', 'km@gmail.com', 'smth');
INSERT INTO users (name, email, password) VALUES ('vova', 'vg@gmail.com', 'password');
INSERT INTO users (name, email, password) VALUES ('nastya', 'ng@gmail.com', 'pass');
INSERT INTO users (name, email, password) VALUES ('veranika', 'verg@gmail.com', 'pass');
INSERT INTO users (name, email, password) VALUES ('sergei', 'sp@gmail.com', 'pass');
INSERT INTO users (name, email, password) VALUES ('admin', 'admin@gmail.com', 'admin');

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (6, 1);


INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 3, 'req');
INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 1, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 4, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (1, 2, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (4, 2, 'fri');

INSERT INTO profiles (about_me, gender, work_country, work_city, home_country, home_city, marital_status,
                      year_of_birth, month_of_birth, day_of_birth, user_id)
VALUES ('i am a kiteboarder', 'MALE', 'Belarus', 'Minsk', 'Belarus', 'Minsk', 'SINGLE', 1976, 07, 12, 2);

INSERT INTO profiles (work_country, work_city, home_country, home_city, marital_status,
                      year_of_birth, month_of_birth, day_of_birth, user_id)
VALUES ('Belarus', 'Minsk', 'Belarus', 'Minsk', 'SINGLE', 1976, 08, 10, 4);

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

INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Вероника, прив', 2, 4);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Вова, привет', 4, 2);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Как дела', 2, 4);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Норм.', 4, 2);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Hello, Nastya.', 4, 3);

