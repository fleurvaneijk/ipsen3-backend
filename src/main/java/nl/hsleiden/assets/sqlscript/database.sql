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
  pregnant boolean,
  week_no  int,
  unique (pregnant, week_no),
  PRIMARY KEY (id),
  CONSTRAINT fk_subject FOREIGN KEY (subject) REFERENCES dilemma_subject (subject) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dilemma_option
(
  id         int,
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
  id              SERIAL,
  parent_email    VARCHAR(60),
  dilemma_id      int,
  rating_time     int,
  rating_dilemma  int,
  PRIMARY KEY (id),
  UNIQUE (parent_email, dilemma_id),
  CONSTRAINT fk_dilemma FOREIGN KEY (dilemma_id) REFERENCES dilemma (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_parent FOREIGN KEY (parent_email) REFERENCES users (email) ON DELETE CASCADE ON UPDATE CASCADE
);