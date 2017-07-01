CREATE DATABASE bl;
USE bl;

DROP TABLE flashmobs;
DROP TABLE events;
DROP TABLE blogs_categories;
DROP TABLE profiles;
DROP TABLE friends;
DROP TABLE messages;
DROP TABLE categories;
DROP TABLE comments;
DROP TABLE blogs;
DROP TABLE users;


CREATE TABLE events (
  id            INT AUTO_INCREMENT,
  name          VARCHAR(255),
  holding_date  VARCHAR(255),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE flashmobs (
  type        VARCHAR(255),
  about_event TEXT,
  event_id    INT,
  PRIMARY KEY (event_id),
  FOREIGN KEY (event_id) REFERENCES events (id)
);


CREATE TABLE users (
  id                INT          AUTO_INCREMENT,
  name              VARCHAR(20),
  email             VARCHAR(20),
  password          VARCHAR(20),
  role              VARCHAR(255) DEFAULT 'user',
  registration_date TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE blogs (
  id            INT                AUTO_INCREMENT,
  title         VARCHAR(255),
  text          TEXT,
  creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_id       INT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
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