-- Volgorde verwijderen tabellen
-- DROP TABLE rating cascade;
-- DROP TABLE answer cascade;
-- DROP TABLE dilemma_option cascade;
-- DROP TABLE dilemma cascade;
-- DROP TABLE dilemma_subject cascade;
-- DROP TABLE admin cascade;
-- DROP TABLE child cascade;
-- DROP TABLE couple cascade;
-- DROP TABLE parent cascade;

CREATE TABLE parent
(
  email     VARCHAR(60),
  firstname VARCHAR(30),
  password  VARCHAR(100),
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
  CONSTRAINT fk_parent1 FOREIGN KEY (parent_email_1) REFERENCES parent (email) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_parent2 FOREIGN KEY (parent_email_2) REFERENCES parent (email) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE child
(
  id        SERIAL,
  couple_id integer,
  birthdate date,
  PRIMARY KEY (id),
  CONSTRAINT fk_couple FOREIGN KEY (couple_id) REFERENCES couple (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE admin
(
  email    VARCHAR(30),
  firstname     VARCHAR(30),
  lastname     VARCHAR(30),
  password VARCHAR(100),
  PRIMARY KEY (email)
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
  parent_email  VARCHAR(60),
  dilemma_id    int,
  answered_time TIMESTAMP,
  answer        int,
  PRIMARY KEY (parent_email, dilemma_id),
  CONSTRAINT fk_dilemma FOREIGN KEY (dilemma_id) REFERENCES dilemma (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_parent FOREIGN KEY (parent_email) REFERENCES parent (email) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rating
(
  parent_email VARCHAR(60),
  dilemma_id   int,
  rating_time      int,
  rating_dilemma   int,
  PRIMARY KEY (parent_email, dilemma_id),
  CONSTRAINT fk_dilemma FOREIGN KEY (dilemma_id) REFERENCES dilemma (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_parent FOREIGN KEY (parent_email) REFERENCES parent (email) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO admin
VALUES ('mijkesmit@dubio.nl', 'Mijke', 'Smit', 'dubio');