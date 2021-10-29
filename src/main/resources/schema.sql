DROP TABLE IF EXISTS client;

create table client
(
   client_Id integer not null,
   full_name varchar(255) not null,
   email varchar(255) not null,
   primary key(client_Id),
   UNIQUE KEY client_email (email)
);

create table reservation
(
   id varchar(255) not null,
   client_Id integer not null,
   arrival_date DATE not null,
   departure_date DATE not null,
   status varchar(20) default 'Active',
   primary key(id)
);

create table reserved
(
   reserved_date DATE not null,
   primary key(reserved_date)
);
