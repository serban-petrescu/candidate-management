CREATE TABLE USER (
  user_id bigint not null auto_increment,
  first_name varchar(255),
  last_name varchar(255),
  email varchar(255),
  username varchar(255),
  password varchar(255),
  active integer,
  PRIMARY KEY (user_id)
  )DEFAULT CHARSET=utf8;
