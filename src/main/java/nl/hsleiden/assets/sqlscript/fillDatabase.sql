-- Insert roles
INSERT INTO user_roles VALUES('OUDER');
INSERT INTO user_roles VALUES('BEHEERDER');
INSERT INTO user_roles VALUES('MEDEWERKER');

-- Couple 1 -- 33 weeks pregnant
INSERT INTO users
VALUES('bob@gmail.com', 'Bob', 'Bakker', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO users
VALUES('els@gmail.com', 'Els', 'Bakker', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO couple
VALUES(DEFAULT, 'bob@gmail.com', 'els@gmail.com', TRUE, 33, 99);

-- Couple 2 -- 35 weeks pregnant
INSERT INTO users
VALUES('karin@gmail.com', 'Karin', 'de Vrij', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO users
VALUES('fred@gmail.com', 'Fred', 'de Boer', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO couple
VALUES(DEFAULT, 'karin@gmail.com', 'fred@gmail.com', TRUE, 34, 99);

-- Couple 3 -- child of 1 week
INSERT INTO users
VALUES('koen@gmail.com', 'Koen', 'Braho', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO users
VALUES('sarah@gmail.com', 'Sarah', 'Trap', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO couple
VALUES(DEFAULT, 'koen@gmail.com', 'sarah@gmail.com', False, 1, 99);
INSERT INTO child VALUES(DEFAULT,(SELECT id FROM couple WHERE parent_email_1 = 'koen@gmail.com'), '2019-01-24');

-- Couple 3 -- child of 3 week
INSERT INTO users
VALUES('annabel@gmail.com', 'Annabel', 'Groeneberg', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO users
VALUES('max@gmail.com', 'Max', 'van der Kooi', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO couple
VALUES(DEFAULT, 'annabel@gmail.com', 'max@gmail.com', False, 3, 99);
INSERT INTO child VALUES(DEFAULT,(SELECT id FROM couple WHERE parent_email_1 = 'annabel@gmail.com'), '2018-01-09');

-- Couple 4 -- for answers
INSERT INTO users
VALUES('tom@hotmail.com', 'Tom', 'Poes', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO users
VALUES('sylvester@hotmail.com', 'Sylvester', 'Kater', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO couple
VALUES(DEFAULT, 'tom@hotmail.com', 'sylvester@hotmail.com', False, 3, 99);
INSERT INTO child VALUES(DEFAULT,(SELECT id FROM couple WHERE parent_email_1 = 'tom@hotmail.com'), '2018-01-09');

-- Couple 5 -- for answers
INSERT INTO users
VALUES('tweety@hotmail.com', 'Tweety', 'Vogel', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO users
VALUES('jerry@hotmail.com', 'Jerry', 'Muis', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'OUDER');
INSERT INTO couple
VALUES(DEFAULT, 'tweety@hotmail.com', 'jerry@hotmail.com', True, 33, 99);

-- Admins
INSERT INTO users
VALUES ('mijke@dubio.nl', 'Mijke', 'Smit', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'BEHEERDER');
INSERT INTO users
VALUES ('loicq@dubio.nl', 'Loicq', 'Rabarison', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'BEHEERDER');

-- Employees
INSERT INTO users
VALUES ('dennis@dubio.nl', 'Dennis', 'van Beelen', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'MEDEWERKER');
INSERT INTO users
VALUES ('fleur@dubio.nl', 'Fleur', 'van Eijk', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'MEDEWERKER');
INSERT INTO users
VALUES ('yme@dubio.nl', 'Yme', 'Brugts', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'MEDEWERKER');
INSERT INTO users
VALUES ('joost@dubio.nl', 'Joost', 'de Winter', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'MEDEWERKER');
INSERT INTO users
VALUES ('robin@dubio.nl', 'Robin', 'Silverio', '$2a$10$DGGzmlsoj2cSOs/jP/QMrO4I.zl5xka3H.STH0H9TjOz7bk7P9ZuG', 'MEDEWERKER');

-- Dilemma subjects / themes
INSERT INTO dilemma_subject VALUES ('sociaal','https://www.24baby.nl/baby/ontwikkeling/baby-stelt-zich-open/', 2);
INSERT INTO dilemma_subject VALUES ('persoonlijk','https://www.24baby.nl/zwanger/sporten-en-zwanger/tips-sporten-zwangerschap/', 1);
INSERT INTO dilemma_subject VALUES ('relatie','https://www.24baby.nl/baby/relatie-ouderschap/hoe-verandert-relatie-krijgen-baby/', 3);
INSERT INTO dilemma_subject VALUES ('opvoeding','https://www.24baby.nl/peuter/gezin-opvoeding/opvoeding-dos-en-donts/', 4);

-- Dilemma 1 -- Child
INSERT INTO dilemma VALUES (DEFAULT, 'opvoeding', FALSE , 1);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/slapendeBaby.png', 'Alle slapende babies die je ziet moet je wakker maken', 1);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/poppenwagen.jpg', 'Je loopt altijd met een poppenwagen rond', 1);

-- Dilemma 2 -- Child
INSERT INTO dilemma VALUES (DEFAULT, 'persoonlijk', FALSE , 2);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/thuisBevallen.jpg', 'Thuis bevallen', 2);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/ziekenhuisBevallen.jpg', 'In het ziekenhuis bevallen', 2);

-- Dilemma 3 -- Child
INSERT INTO dilemma VALUES (DEFAULT, 'relatie', FALSE , 3);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/vetvrij.jpg', 'Een gezond dieeet', 3);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/geenDieet.jpg', 'Lekker vet', 3);

-- Dilemma 4 -- Child
INSERT INTO dilemma VALUES (DEFAULT, 'sociaal', FALSE , 4);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/yoga.jpg', 'Yoga', 4);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/kickboksen.jpg', 'Boxen', 4);

-- Dilemma 5 -- Child
INSERT INTO dilemma VALUES (DEFAULT, 'persoonlijk', FALSE , 5);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/vetvrij.jpg', 'Een gezond dieeet', 5);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/geenDieet.jpg', 'Lekker vet', 5);

-- Dilemma 6 -- Pregnant -- week 32
INSERT INTO dilemma VALUES (DEFAULT, 'sociaal', TRUE , 32);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/slapendeBaby.png', 'Alle slapende babies die je ziet moet je wakker maken', 6);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/poppenwagen.jpg', 'Je loopt altijd met een poppenwagen rond', 6);

-- Dilemma 7 -- Pregnant -- 33
INSERT INTO dilemma VALUES (DEFAULT, 'relatie', TRUE , 33);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/thuisBevallen.jpg', 'Thuis bevallen', 7);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/ziekenhuisBevallen.jpg', 'In het ziekenhuis bevallen', 7);

-- Dilemma 8 -- Pregnant -- 34
INSERT INTO dilemma VALUES (DEFAULT, 'persoonlijk', TRUE , 34);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/vetvrij.jpg', 'Een gezond dieeet', 8);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/geenDieet.jpg', 'Lekker vet', 8);

-- Dilemma 9 -- Pregnant -- 35
INSERT INTO dilemma VALUES (DEFAULT, 'sociaal', TRUE , 35);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/yoga.jpg', 'Yoga', 9);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/kickboksen.jpg', 'Boxen', 9);

-- Dilemma 10 -- Pregnant -- 36
INSERT INTO dilemma VALUES (DEFAULT, 'opvoeding', TRUE , 36);
INSERT INTO dilemma_option
VALUES (1, '../../../assets/images/Dilemma_images/vetvrij.jpg', 'Een gezond dieeet', 10);
INSERT INTO dilemma_option
VALUES (2, '../../../assets/images/Dilemma_images/geenDieet.jpg', 'Lekker vet', 10);


-- answers
INSERT INTO answer VALUES ('tom@hotmail.com', 1, '2018-12-10 09:10:25', 0);
INSERT INTO answer VALUES ('tom@hotmail.com', 2, '2018-12-18 09:10:25', 1);
INSERT INTO answer VALUES ('tom@hotmail.com', 3, '2018-12-27 09:10:23', 1);
INSERT INTO answer VALUES ('tom@hotmail.com', 4, '2018-12-31 09:10:25', 0);
INSERT INTO answer VALUES ('tom@hotmail.com', 5, '2019-01-07 01:10:25', 1);
INSERT INTO answer VALUES ('tom@hotmail.com', 6, '2019-01-15 20:10:25', 0);
INSERT INTO answer VALUES ('tom@hotmail.com', 7, '2019-01-23 09:10:23', 1);
INSERT INTO answer VALUES ('tom@hotmail.com', 8, '2019-01-31 20:10:25', 1);
INSERT INTO answer VALUES ('tom@hotmail.com', 9, '2018-12-11 13:10:25', 1);
INSERT INTO answer VALUES ('tom@hotmail.com', 10, '2018-12-23 14:10:25', 0);

INSERT INTO answer VALUES ('sylvester@hotmail.com', 1, '2018-12-10 09:10:25', 1);
INSERT INTO answer VALUES ('sylvester@hotmail.com', 2, '2018-12-18 09:10:25', 0);
INSERT INTO answer VALUES ('sylvester@hotmail.com', 3, '2018-12-27 09:10:23', 1);
INSERT INTO answer VALUES ('sylvester@hotmail.com', 4, '2018-12-31 09:10:25', 1);
INSERT INTO answer VALUES ('sylvester@hotmail.com', 5, '2019-01-07 01:10:25', 0);
INSERT INTO answer VALUES ('sylvester@hotmail.com', 6, '2019-01-15 20:10:25', 0);
INSERT INTO answer VALUES ('sylvester@hotmail.com', 7, '2019-01-23 09:10:23', 0);
INSERT INTO answer VALUES ('sylvester@hotmail.com', 8, '2019-01-31 20:10:25', 0);
INSERT INTO answer VALUES ('sylvester@hotmail.com', 9, '2018-12-11 13:10:25', 1);

INSERT INTO answer VALUES ('jerry@hotmail.com', 1, '2018-12-10 18:10:25', 0);
INSERT INTO answer VALUES ('jerry@hotmail.com', 2, '2018-12-19 09:10:25', 1);
INSERT INTO answer VALUES ('jerry@hotmail.com', 3, '2018-12-27 09:10:23', 1);
INSERT INTO answer VALUES ('jerry@hotmail.com', 4, '2018-12-31 20:10:25', 0);
INSERT INTO answer VALUES ('jerry@hotmail.com', 5, '2019-01-08 01:10:25', 0);
INSERT INTO answer VALUES ('jerry@hotmail.com', 6, '2019-01-15 20:10:25', 1);
INSERT INTO answer VALUES ('jerry@hotmail.com', 7, '2019-01-23 20:10:23', 1);
INSERT INTO answer VALUES ('jerry@hotmail.com', 8, '2019-01-30 20:10:25', 1);
INSERT INTO answer VALUES ('jerry@hotmail.com', 9, '2018-12-11 13:10:25', 1);

INSERT INTO answer VALUES ('tweety@hotmail.com', 1, '2018-12-10 18:10:25', 1);
INSERT INTO answer VALUES ('tweety@hotmail.com', 2, '2018-12-19 09:10:25', 0);
INSERT INTO answer VALUES ('tweety@hotmail.com', 3, '2018-12-27 09:10:23', 1);
INSERT INTO answer VALUES ('tweety@hotmail.com', 4, '2018-12-31 20:10:25', 1);
INSERT INTO answer VALUES ('tweety@hotmail.com', 5, '2019-01-08 01:10:25', 0);
INSERT INTO answer VALUES ('tweety@hotmail.com', 6, '2019-01-15 20:10:25', 0);
INSERT INTO answer VALUES ('tweety@hotmail.com', 7, '2019-01-23 20:10:23', 1);
INSERT INTO answer VALUES ('tweety@hotmail.com', 8, '2019-01-30 20:10:25', 0);

-- ratings
insert into rating values(DEFAULT, 'tom@hotmail.com', '1', '4', '3');
insert into rating values(DEFAULT, 'tom@hotmail.com', '2', '2', '5');
insert into rating values(DEFAULT, 'tom@hotmail.com', '3', '5', '4');
insert into rating values(DEFAULT, 'tom@hotmail.com', '4', '3', '2');
insert into rating values(DEFAULT, 'tom@hotmail.com', '5', '4', '3');
insert into rating values(DEFAULT, 'tom@hotmail.com', '6', '2', '5');
insert into rating values(DEFAULT, 'tom@hotmail.com', '7', '5', '4');
insert into rating values(DEFAULT, 'tom@hotmail.com', '8', '3', '2');
insert into rating values(DEFAULT, 'tom@hotmail.com', '9', '3', '2');
insert into rating values(DEFAULT, 'tom@hotmail.com', '10', '3', '2');

insert into rating values(DEFAULT, 'sylvester@hotmail.com', '1', '4', '3');
insert into rating values(DEFAULT, 'sylvester@hotmail.com', '2', '2', '5');
insert into rating values(DEFAULT, 'sylvester@hotmail.com', '3', '5', '4');
insert into rating values(DEFAULT, 'sylvester@hotmail.com', '4', '3', '2');
insert into rating values(DEFAULT, 'sylvester@hotmail.com', '5', '4', '3');
insert into rating values(DEFAULT, 'sylvester@hotmail.com', '6', '2', '5');
insert into rating values(DEFAULT, 'sylvester@hotmail.com', '7', '5', '4');
insert into rating values(DEFAULT, 'sylvester@hotmail.com', '8', '3', '2');
insert into rating values(DEFAULT, 'sylvester@hotmail.com', '9', '3', '2');

insert into rating values(DEFAULT, 'jerry@hotmail.com', '1', '5', '4');
insert into rating values(DEFAULT, 'jerry@hotmail.com', '2', '4', '2');
insert into rating values(DEFAULT, 'jerry@hotmail.com', '3', '3', '3');
insert into rating values(DEFAULT, 'jerry@hotmail.com', '4', '4', '5');
insert into rating values(DEFAULT, 'jerry@hotmail.com', '5', '5', '4');
insert into rating values(DEFAULT, 'jerry@hotmail.com', '6', '4', '2');
insert into rating values(DEFAULT, 'jerry@hotmail.com', '7', '3', '3');
insert into rating values(DEFAULT, 'jerry@hotmail.com', '8', '4', '5');
insert into rating values(DEFAULT, 'jerry@hotmail.com', '9', '3', '3');

insert into rating values(DEFAULT, 'tweety@hotmail.com', '1', '5', '4');
insert into rating values(DEFAULT, 'tweety@hotmail.com', '2', '4', '2');
insert into rating values(DEFAULT, 'tweety@hotmail.com', '3', '3', '3');
insert into rating values(DEFAULT, 'tweety@hotmail.com', '4', '4', '5');
insert into rating values(DEFAULT, 'tweety@hotmail.com', '5', '5', '4');
insert into rating values(DEFAULT, 'tweety@hotmail.com', '6', '4', '2');
insert into rating values(DEFAULT, 'tweety@hotmail.com', '7', '3', '3');
insert into rating values(DEFAULT, 'tweety@hotmail.com', '8', '4', '5');