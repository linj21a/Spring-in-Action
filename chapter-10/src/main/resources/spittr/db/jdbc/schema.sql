drop table if exists spittle;
drop table if exists spitter;
-- H2 Database 是一个 Java 实现的关系型数据库，也是一个嵌入式数据库。
create table spitter(
    id integer primary key auto_increment,
    firstName varchar(10) not null comment  "姓",
    lastName varchar(10) not null comment "名",
    username varchar(20) not null comment "用户名",
    password varchar(20) not null comment "密码",
    email varchar(50) not null comment "邮箱"
);

create table spittle (
     id integer primary key,
     spitterid integer not null,
     message varchar(2000) not null,
     time datetime not null,
     latitude double comment "经度",
     longitude double comment "维度",
     foreign key (spitterid) references spitter(id) -- 外键参考到spitter的id
);