create table topicos(

id bigint not null auto_increment,
usuario varchar(100) not null,
titulo varchar(100) not null unique,
mensaje varchar(300) not null unique,
curso varchar(100) not null,
fecha varchar(100) not null,
activo tinyint not null,

primary key(id)
);