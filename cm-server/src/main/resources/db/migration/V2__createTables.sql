-- this is the first sql file to be execute from flyway; it starts with V2 (not V1) because of the baseline-on-migrate property
-- which ignores the V1 file if the database schema is not empty

DROP TABLE IF EXISTS candidate_notes;
DROP TABLE IF EXISTS candidate_skills;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS candidate;
DROP TABLE IF EXISTS education;

CREATE TABLE education (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  duration int(11) NOT NULL,
  education_type varchar(255) DEFAULT NULL,
  provider varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE candidate (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  event varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  original_study_year int(11) NOT NULL,
  education_status varchar(255) DEFAULT NULL,
  education_id bigint(20) DEFAULT NULL,
  date_of_adding datetime DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_education FOREIGN KEY (education_id) REFERENCES education (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE candidate_notes (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  date datetime DEFAULT NULL,
  note varchar(255) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  candidate_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_candidate FOREIGN KEY (candidate_id) REFERENCES candidate (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE tag (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  tag_type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE candidate_skills (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  certifier varchar(255) DEFAULT NULL,
  rating varchar(255) DEFAULT NULL,
  candidate_id bigint(20) NOT NULL,
  tag_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_tag FOREIGN KEY (tag_id) REFERENCES tag (id),
  CONSTRAINT FK_candidate_2 FOREIGN KEY (candidate_id) REFERENCES candidate (id)
) DEFAULT CHARSET=utf8;


