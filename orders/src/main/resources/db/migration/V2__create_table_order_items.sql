CREATE TABLE order_item (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  quantity int(11) NOT NULL,
  orderrequest_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (orderrequest_id) REFERENCES orderrequest(id)
)