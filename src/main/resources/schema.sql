
CREATE TABLE IF NOT EXISTS client
(
   id bigint(20) NOT NULL AUTO_INCREMENT,
   full_name varchar(255) not null,
   email varchar(255) not null,
   primary key(id),
   UNIQUE KEY client_email (email)
);

CREATE TABLE IF NOT EXISTS reservation
(
   id varchar(255) not null,
   client_Id bigint(20) not null,
   arrival_date DATE not null,
   departure_date DATE not null,
   status varchar(20) default 'Active',
   primary key(id)
);

CREATE TABLE IF NOT EXISTS reserved
(
   reserved_date DATE not null,
   primary key(reserved_date)
);

INSERT INTO reserved(reserved_date ) VALUES('2021-11-05');
INSERT INTO client(full_name, email ) VALUES('Admin', 'volcano.admin@gmail.com');