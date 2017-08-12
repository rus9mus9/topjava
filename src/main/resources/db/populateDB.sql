DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (calories, description, dateTime, user_id) VALUES
  (500, 'Завтрак', '2017-08-10 10:00:00', 100000),
  (1000, 'Обед', '2017-08-10 14:00:00', 100000),
  (250, 'Ужин', '2017-08-10 19:00:00', 100000),
  (500, 'Завтрак', '2017-08-10 12:00:00', 100001),
  (500, 'Обед', '2017-08-10 16:00:00', 100001);
