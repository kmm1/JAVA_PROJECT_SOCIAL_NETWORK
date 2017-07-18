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
  name              VARCHAR(20) UNIQUE,
  email             VARCHAR(20) UNIQUE,
  password          VARCHAR(255),
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
  name VARCHAR(50) UNIQUE,
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


INSERT INTO events (id, name, holding_date, version) VALUES (1, 'Я ♥ Water Battle!', '22 июля-15 августа', 0);
INSERT INTO flashmobs (type, about_event, event_id) VALUES ('FLASHMOB', 'Все любители массовых игрищ и развлечений,
где нужно помокнуть,повеселиться и побороться, в том числе и с оружием (умолчим, что оно водное), непременно следите
за информацией о проведении сборищ!', 1);
INSERT INTO events (id, name, holding_date, version)
VALUES (2, 'Как насчет вокального флэшмоба?? Есть поющие??))', '15 августа', 0);
INSERT INTO flashmobs (type, about_event, event_id) VALUES ('FLASHMOB', 'Хотим снять видео с прикольным флешмобом .
Надо будет танцевать под эту песню . Нужны девушки и парни . Все это будет на открытой территории в разных местах .',
                                                            2);


INSERT INTO users (name, email, password)
VALUES ('kate', 'km@gmail.com', '$2a$10$gqVmsBZBvbWyBnBnouJcdOrFmoZ/aNze6pc3L2bgrdPFankGJgmf6');
INSERT INTO users (name, email, password)
VALUES ('vova', 'vg@gmail.com', '$2a$10$gqVmsBZBvbWyBnBnouJcdOrFmoZ/aNze6pc3L2bgrdPFankGJgmf6');
INSERT INTO users (name, email, password)
VALUES ('nastya', 'ng@gmail.com', '$2a$10$gqVmsBZBvbWyBnBnouJcdOrFmoZ/aNze6pc3L2bgrdPFankGJgmf6');
INSERT INTO users (name, email, password)
VALUES ('veranika', 'verg@gmail.com', '$2a$10$gqVmsBZBvbWyBnBnouJcdOrFmoZ/aNze6pc3L2bgrdPFankGJgmf6');
INSERT INTO users (name, email, password)
VALUES ('sergei', 'sp@gmail.com', '$2a$10$gqVmsBZBvbWyBnBnouJcdOrFmoZ/aNze6pc3L2bgrdPFankGJgmf6');
INSERT INTO users (name, email, password)
VALUES ('admin', 'admin@gmail.com', '$2a$10$gqVmsBZBvbWyBnBnouJcdOrFmoZ/aNze6pc3L2bgrdPFankGJgmf6');
# password for users in BCRYPT: smth

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (6, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (6, 2);


INSERT INTO friends (friend_one, friend_two, status) VALUES (1, 2, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (1, 6, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (1, 5, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (1, 4, 'req');
INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 4, 'fri');
INSERT INTO friends (friend_one, friend_two, status) VALUES (2, 3, 'req');


INSERT INTO profiles (about_me, gender, work_country, work_city, home_country, home_city, marital_status,
                      year_of_birth, month_of_birth, day_of_birth, user_id)
VALUES ('i am a kiteboarder', 'MALE', 'Belarus', 'Minsk', 'Belarus', 'Minsk', 'SINGLE', 1976, 07, 12, 2);

INSERT INTO profiles (work_country, work_city, home_country, home_city,
                      year_of_birth, month_of_birth, day_of_birth, user_id)
VALUES ('Belarus', 'Minsk', 'Belarus', 'Minsk', 1976, 08, 10, 1);

INSERT INTO categories (name) VALUES ('SPORT');
INSERT INTO categories (name) VALUES ('TRAVEL');
INSERT INTO categories (name) VALUES ('BUSINESS');
INSERT INTO categories (name) VALUES ('RELIGION');
INSERT INTO categories (name) VALUES ('SCIENCE');
INSERT INTO categories (name) VALUES ('CULTURE');
INSERT INTO categories (name) VALUES ('DIFFERENT');


INSERT INTO blogs (title, text, user_id) VALUES ('FLYSURFER KITES', 'Новый кайт от компании Флайсерфер.
Flysurfer порадовал своих поклонников новинкой. Чисто зимним и ленд кайтом Outlow. Этот кайт обещает посоперничатьс самыми известными собратьями по скорости и стабильности. Ну а качества ему не занимать.

Короткая информация об OUTLAW

Приятно сообщить о новом дополнении к нашему Flysurfer –у.

Как раз вовремя для зимнего сезона мы представляем вам наш новый OUTLAW.


OUTLAW- кайт с открытыми ячейками , предназначенный для катания по обычной
и заснеженной поверхности. Выполнен с высшим качеством Flysurfer-а.

OUTLAW- это новый дизайн в нашем составе с великолепными летными
характеристиками, управлением и скоростью на повороте.

OUTLAW дарит райдеру устойчивую и безопасную платформу для осуществления
своих трюков без жертв в управлении.

Со своей высокой скоростью при повороте и узким радиусом поворота , на
OUTLAW так просто забраться на гору , как никогда прежде!

Тяга на м2 на очень высоком уровне, что означает : можно взять кайт
меньшего размера , чем запланировано.

OUTLAW включает все самые новейшие технологии Flysurfer-а и технические
характеристики, принеся на рынок 3 новшества в дизайне кайта с открытыми
ячейками!

- Полная система депауэра предлагает больше тяги и снятия тяги по
необходимости !

создав лучший в промышленности кайт для катания по обычной и заснеженной
поверхности

- Запатентованная технология щелевых закрылок была встроена в OUTLAW , что
в приложении к кайту увеличивает тягу и устойчивость . Щелевые
акрылки - существенная часть при увеличении подъема и снижении скорости ,
что обеспечивает безопасное катание', 2);
INSERT INTO blogs (title, text, user_id) VALUES ('KITEBOARDING', 'All abou Kites', 2);
INSERT INTO blogs (title, text, user_id) VALUES ('Traveling', 'I write about travelling', 3);

INSERT INTO blogs_categories (blog_id, category_id) VALUES (1, 1);
INSERT INTO blogs_categories (blog_id, category_id) VALUES (2, 1);
INSERT INTO blogs_categories (blog_id, category_id) VALUES (3, 2);

INSERT INTO comments (comment, user_id, blog_id) VALUES ('Nice blog', 4, 1);
INSERT INTO comments (comment, user_id, blog_id) VALUES ('Keep writing', 3, 1);

INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Вероника, прив', 2, 4);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Вова, привет', 4, 2);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Как дела', 2, 4);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Норм.', 4, 2);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Hello, Nastya.', 4, 3);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('привет', 1, 4);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Как дела', 4, 1);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Норм.', 1, 4);
INSERT INTO messages (text, user_sender_id, user_receiver_id) VALUES ('Happy Birthday', 1, 3);

