-- Volgorde verwijderen tabellen
-- drop table child cascade;
-- drop table couple cascade;
-- drop table users cascade;
-- drop table rating cascade;
-- drop table dilemma_subject cascade;
-- drop table dilemma_option cascade;
-- drop table dilemma cascade;
-- drop table answer cascade;
-- drop table rating cascade;
-- drop table user_roles cascade;

CREATE TABLE user_roles
(
  role VARCHAR(25) PRIMARY KEY
);

CREATE TABLE users (
  email     VARCHAR(60),
  firstname VARCHAR(30),
  lastname  VARCHAR(30),
  password  VARCHAR(100),
  role      VARCHAR(25) REFERENCES user_roles(role),
  PRIMARY KEY (email)
);

CREATE TABLE couple
(
  id                  SERIAL,
  parent_email_1      VARCHAR(60) UNIQUE,
  parent_email_2      VARCHAR(60) UNIQUE,
  pregnant            boolean DEFAULT NULL,
  weeks_pregnant      integer,
  last_answer_week_no INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_parent1 FOREIGN KEY (parent_email_1) REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_parent2 FOREIGN KEY (parent_email_2) REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE child
(
  id        SERIAL,
  couple_id integer,
  birthdate date,
  PRIMARY KEY (id),
  CONSTRAINT fk_couple FOREIGN KEY (couple_id) REFERENCES couple (id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE dilemma_subject
(
  subject       VARCHAR(30),
  link          VARCHAR(255),
  number_clicks INTEGER,
  PRIMARY KEY (subject)
);

CREATE TABLE dilemma
(
  id       SERIAL,
  subject  VARCHAR(30),
  pregnant boolean NOT NULL,
  week_no  int     NOT NULL,
  unique (pregnant, week_no),
  PRIMARY KEY (id),
  CONSTRAINT fk_subject FOREIGN KEY (subject) REFERENCES dilemma_subject (subject) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dilemma_option
(
  id         SERIAL,
  image      VARCHAR(100),
  text       VARCHAR(255),
  dilemma_id int,
  CONSTRAINT fk_dilemma FOREIGN KEY (dilemma_id) REFERENCES dilemma (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE answer
(
  parent_email    VARCHAR(60),
  dilemma_id      int,
  answered_time   TIMESTAMP,
  answer          int,
  PRIMARY KEY (parent_email, dilemma_id),
  CONSTRAINT fk_dilemma FOREIGN KEY (dilemma_id) REFERENCES dilemma (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_parent FOREIGN KEY (parent_email) REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rating
(
  parent_email    VARCHAR(60),
  dilemma_id      int,
  rating_time     int,
  rating_dilemma  int,
  PRIMARY KEY (parent_email, dilemma_id),
  CONSTRAINT fk_dilemma FOREIGN KEY (dilemma_id) REFERENCES dilemma (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_parent FOREIGN KEY (parent_email) REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Insert tests
INSERT INTO user_roles VALUES('PARENT');
INSERT INTO user_roles VALUES('ADMIN');
INSERT INTO user_roles VALUES('MEDEWERKER');

-- User 1
INSERT INTO users
VALUES('robinsilverio@hotmail.com', 'Robin', 'Silvério', 'dubio100', 'PARENT');
INSERT INTO users
VALUES('fleur@hotmail.com', 'Fleur', 'van Eijk', 'dubio100', 'PARENT');
INSERT INTO couple
VALUES(DEFAULT, 'robinsilverio@hotmail.com', 'fleur@hotmail.com', TRUE, 30, 30);

-- User 2
INSERT INTO users
VALUES('yme@hotmail.com', 'Yme', 'Brugts', 'dubio100', 'PARENT');
INSERT INTO users
VALUES('dilisha@hotmail.com', 'Dilisha', 'weetgeenachternaam', 'dubio100', 'PARENT');
INSERT INTO couple
VALUES(DEFAULT, 'yme@hotmail.com', 'dilisha@hotmail.com', FALSE, 0, 36);

INSERT INTO child VALUES(DEFAULT,(SELECT id FROM couple WHERE parent_email_1 = 'yme@hotmail.com'), '2019-01-14');

-- Users 3 met kleine wachtwoorden (wachtwoord is abc).
INSERT INTO users
VALUES('koen@gmail.com', 'Koen', 'Warner', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'PARENT');

INSERT INTO users
VALUES('gulag@ussr.com', 'Josef', 'Stalin', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'PARENT');

INSERT INTO couple
VALUES(DEFAULT, 'koen@gmail.com', 'gulag@ussr.com', TRUE, 30, 30);

INSERT INTO child VALUES(DEFAULT,(SELECT id FROM couple WHERE parent_email_1 = 'koen@gmail.com'), '2019-01-14');

-- Admin met kleine wachtwoord (wachtwoord is abc).
INSERT INTO users
VALUES ('mijkesmit@dubio.nl', 'Mijke', 'Smit', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'ADMIN');
INSERT INTO users
VALUES ('loicq@dubio.nl', 'Loicq', 'Rabarison', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'MEDEWERKER');

-- dilemma voorbeelden
INSERT INTO dilemma_subject VALUES ('sociaal','www.link.nl/sociaal', 1);
INSERT INTO dilemma_subject VALUES ('persoonlijk','www.link.nl/persoonlijk', 1);
INSERT INTO dilemma_subject VALUES ('relatie','www.link.nl/relatie', 1);
INSERT INTO dilemma_subject VALUES ('opvoeding','www.link.nl/opvoeding', 1);

INSERT INTO dilemma VALUES (1,'sociaal',FALSE ,1);
INSERT INTO dilemma VALUES (2,'persoonlijk',FALSE ,2);
INSERT INTO dilemma VALUES (3,'relatie',FALSE ,3);
INSERT INTO dilemma VALUES (4,'opvoeding',FALSE ,4);
INSERT INTO dilemma VALUES (5,'sociaal',TRUE ,1);
INSERT INTO dilemma VALUES (6,'persoonlijk',TRUE ,2);
INSERT INTO dilemma VALUES (7,'relatie',TRUE ,3);
INSERT INTO dilemma VALUES (8,'opvoeding',TRUE ,4);
INSERT INTO dilemma VALUES (9,'sociaal',TRUE ,5);

parent_email    VARCHAR(60),
  dilemma_id      int,
  answered_time   TIMESTAMP,
  answer          int,

INSERT INTO answer VALUES ('fleur@hotmail.com', 1, '2018-12-10 09:10:25', 1);
INSERT INTO answer VALUES ('fleur@hotmail.com', 2, '2018-12-18 09:10:25', 2);
INSERT INTO answer VALUES ('fleur@hotmail.com', 3, '2018-12-27 09:10:23', 1);
INSERT INTO answer VALUES ('fleur@hotmail.com', 4, '2018-12-31 09:10:25', 1);
INSERT INTO answer VALUES ('fleur@hotmail.com', 5, '2019-01-07 01:10:25', 1);
INSERT INTO answer VALUES ('fleur@hotmail.com', 6, '2019-01-15 20:10:25', 2);
INSERT INTO answer VALUES ('fleur@hotmail.com', 7, '2019-01-23 20:10:23', 1);
INSERT INTO answer VALUES ('fleur@hotmail.com', 8, '2019-01-31 20:10:25', 1);
INSERT INTO answer VALUES ('yme@hotmail.com', 1, '2018-12-11 13:10:25', 1);
INSERT INTO answer VALUES ('yme@hotmail.com', 2, '2018-12-23 14:10:25', 2);
INSERT INTO answer VALUES ('yme@hotmail.com', 3, '2018-12-24 15:10:23', 1);
INSERT INTO answer VALUES ('yme@hotmail.com', 4, '2018-12-31 21:10:25', 1);
INSERT INTO answer VALUES ('yme@hotmail.com', 5, '2019-01-07 21:10:25', 1);
INSERT INTO answer VALUES ('yme@hotmail.com', 6, '2019-01-15 09:10:25', 2);
INSERT INTO answer VALUES ('yme@hotmail.com', 7, '2019-01-21 09:10:23', 1);
INSERT INTO answer VALUES ('yme@hotmail.com', 8, '2019-01-29 18:10:25', 1);

insert into rating values('fleur@hotmail.com', '1', '4', '3');
insert into rating values('fleur@hotmail.com', '2', '2', '5');
insert into rating values('fleur@hotmail.com', '3', '5', '4');
insert into rating values('fleur@hotmail.com', '4', '3', '2');
insert into rating values('yme@hotmail.com', '1', '5', '4');
insert into rating values('yme@hotmail.com', '2', '4', '2');
insert into rating values('yme@hotmail.com', '3', '3', '3');
insert into rating values('yme@hotmail.com', '4', '4', '5');