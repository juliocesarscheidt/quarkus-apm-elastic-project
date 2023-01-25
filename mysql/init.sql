USE quarkus_api;

create table message (
  id int auto_increment primary key,
  user_id int not null,
  content varchar(1024) not null,
  created_at datetime,
  updated_at datetime,
  deleted_at datetime
);
