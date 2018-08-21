CREATE TABLE IF NOT EXISTS education (
  id bigserial NOT NULL,
  description varchar(255) DEFAULT NULL,
  duration int NOT NULL,
  education_type varchar(255) DEFAULT NULL,
  provider varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS candidate (
  id bigserial NOT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  event varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  original_study_year int NOT NULL,
  education_status varchar(255) DEFAULT NULL,
  education_id bigint DEFAULT NULL,
  date_of_adding date DEFAULT NULL,
  check_candidate VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_education FOREIGN KEY (education_id) REFERENCES EDUCATION (id)
);

CREATE TABLE IF NOT EXISTS candidate_notes (
  id bigserial NOT NULL,
  date date DEFAULT NULL,
  note varchar(255) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  candidate_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_candidate FOREIGN KEY (candidate_id) REFERENCES CANDIDATE (id)
);

CREATE TABLE IF NOT EXISTS tag (
  id bigserial NOT NULL,
  description varchar(255) DEFAULT NULL,
  tag_type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS candidate_skills (
  id bigserial NOT NULL,
  certifier varchar(255) DEFAULT NULL,
  rating varchar(255) DEFAULT NULL,
  candidate_id bigint NOT NULL,
  tag_id bigint NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_tag FOREIGN KEY (tag_id) REFERENCES TAG (id),
  CONSTRAINT FK_candidate_2 FOREIGN KEY (candidate_id) REFERENCES CANDIDATE (id)
);
