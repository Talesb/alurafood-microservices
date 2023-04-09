CREATE TABLE  orderrequest(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  date_time_order datetime NOT NULL,
  status varchar(255) NOT NULL,
  PRIMARY KEY (id)
)