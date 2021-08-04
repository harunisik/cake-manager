CREATE TABLE cake (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(40) NOT NULL,
  description varchar(40) NOT NULL,
  created_by varchar(40) NOT NULL,
  created_date datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
