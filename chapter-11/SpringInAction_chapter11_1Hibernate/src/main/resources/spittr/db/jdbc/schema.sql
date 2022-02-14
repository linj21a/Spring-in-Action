drop table if exists spittle;
drop table if exists spitter;
-- H2 Database 是一个 Java 实现的关系型数据库，也是一个嵌入式数据库。
create table spitter(
    id identity,
    firstName varchar(10) not null  ,
    lastName varchar(10) not null ,
    username varchar(20) not null ,
    password varchar(20) not null ,
    email varchar(50) not null
);

create table spittle (
     id integer identity primary key,
     spitterid integer not null,
     message varchar(2000) not null,
     time datetime not null,
     latitude double ,
     longitude double,
     foreign key (spitterid) references spitter(id) -- 外键参考到spitter的id
);