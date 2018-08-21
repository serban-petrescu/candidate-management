INSERT INTO education(id, description, duration, education_type, provider) VALUES (1001,'Computer Science',4,'Bachelor','UTCN');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1002,'Automation',4,'Bachelor','UTCN');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1003,'Telecommunication',4,'Bachelor','UTCN');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1004,'Mecathronics',4,'Bachelor','UTCN');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1005,'Others',4,'Bachelor','UTCN');

INSERT INTO education(id, description, duration, education_type, provider) VALUES (1011,'Computer Science',3,'Bachelor','UBB');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1012,'Mathematics-Informatics',3,'Bachelor','UBB');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1013,'Mathematics',3,'Bachelor','UBB');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1014,'Information Economics',3,'Bachelor','UBB');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1015,'Cybernetics',3,'Bachelor','UBB');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1016,'Others',3,'Bachelor','UBB');

INSERT INTO education(id, description, duration, education_type, provider) VALUES (1021,'Computer Science',3,'Bachelor','Sapientia');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1022,'Computer Engineering',4,'Bachelor','Sapientia');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1023,'Telecommunication',4,'Bachelor','Sapientia');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1024,'Automation',4,'Bachelor','Sapientia');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1025,'Mecathronics',4,'Bachelor','Sapientia');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1026,'Others',4,'Bachelor','Sapientia');


INSERT INTO education(id, description, duration, education_type, provider) VALUES (1031,'Computer Science',4,'Bachelor','Petru Maior');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1032,'Automation',4,'Bachelor','Petru Maior');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1033,'Others',4,'Bachelor','Petru Maior');

INSERT INTO education(id, description, duration, education_type, provider) VALUES (1041,'Computer Science',3,'Bachelor','UVT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1042,'Mathematics-Informatics',3,'Bachelor','UVT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1043,'Mathematics',3,'Bachelor','UVT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1044,'Others',3,'Bachelor','UVT');


INSERT INTO education(id, description, duration, education_type, provider) VALUES (1051,'Computer Science',4,'Bachelor','UPT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1052,'Automation',4,'Bachelor','UPT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1053,'Information Technologies',4,'Bachelor','UPT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1054,'Others',4,'Bachelor','UPT');


INSERT INTO education(id, description, duration, education_type, provider) VALUES (1061,'Front End Web',1,'Bachelor','Scoala Informala de IT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1062,'Database',1,'Bachelor','Scoala Informala de IT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1063,'Testing',1,'Bachelor','Scoala Informala de IT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1064,'ABAP',1,'Bachelor','Scoala Informala de IT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1065,'Business Analyst',1,'Bachelor','Scoala Informala de IT');
INSERT INTO education(id, description, duration, education_type, provider) VALUES (1066,'Others',1,'Bachelor','Scoala Informala de IT');


INSERT INTO education(id, description, duration, education_type, provider) VALUES (1071,'Others',4,'Bachelor','Others');
SELECT pg_catalog.setval(pg_get_serial_sequence('education', 'id'), MAX(id)) FROM education;

