INSERT INTO tb_perfis (name)
VALUES ('ROLE_USER');
INSERT INTO tb_perfis (name)
VALUES ('ROLE_MODERATOR');

INSERT INTO tb_user (email, name, password)
VALUES ('ana@email.com', 'ana', '$2a$10$ko8dI1Gz1psqYFu7E9EthuKPKnLQJSl.hVbMc17PIq7B7HBT8TzMS');
INSERT INTO tb_user (email, name, password)
VALUES ('moderator@email.com', 'moderator', '$2a$10$b98RXR.vE6scS52lp0xOlujjznUh8ihElQCnFIZJuOwr70uqwyneu');

INSERT INTO tb_user_perfis (user_id, perfis_id)
VALUES (1, 1);
INSERT INTO tb_user_perfis (user_id, perfis_id)
VALUES (2, 2);

INSERT INTO tb_course(category, name)
VALUES ('DevOps', 'IAC');
INSERT INTO tb_course(category, name)
VALUES ('DevOps', 'AWS');
INSERT INTO tb_course(category, name)
VALUES ('Developer', 'Java EE');
INSERT INTO tb_course(category, name)
VALUES ('Developer', 'Java Web');
INSERT INTO tb_course(category, name)
VALUES ('Developer', 'Java JDBC');

INSERT INTO tb_topic (created_date, message, status, title, author_id, course_id)
VALUES ('2022-03-05 03:18:34.359286', 'Cannot update that using cmd', 'NOT_ANSWERED', 'Console log', 1, 1);
INSERT INTO tb_topic (created_date, message, status, title, author_id, course_id)
VALUES ('2022-03-06 11:18:34.359286', 'Unable to access private user key', 'NOT_ANSWERED', 'AWS login', 2, 2);
INSERT INTO tb_topic (created_date, message, status, title, author_id, course_id)
VALUES ('2022-03-07 22:18:34.359286', 'Can''t load the class using reflection', 'NOT_ANSWERED', 'Java Reflection Class',
        1, 3);
INSERT INTO tb_topic (created_date, message, status, title, author_id, course_id)
VALUES ('2022-03-08 10:18:34.359286', 'link localhost:3306 not working', 'NOT_ANSWERED', 'Java JDBC', 2, 4);
INSERT INTO tb_topic (created_date, message, status, title, author_id, course_id)
VALUES ('2022-03-09 06:18:34.359286', 'Can''t load the class using reflection', 'NOT_ANSWERED', 'Java WEB API', 1, 5);

