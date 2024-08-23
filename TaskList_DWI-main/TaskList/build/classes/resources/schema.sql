--drop database if exists tasklist;
--create database tasklist;
--use tasklist;
drop all objects;
create table usuario (
    id int not null auto_increment,
    name varchar (60) not null,
    email varchar(40) not null,
    senha varchar (50) not null,
    primary key (id)
);
alter table usuario add constraint usuario_email_uniq unique(email);

create table task (
	id int not null auto_increment,
	text varchar (100) not null,
	deadline date not null,
	status varchar(20) not null,
	primary key (id)
);	

create table task_usuario (
	id int not null auto_increment,
	usuario int not null references usuario(id),
	task int not null references task(id),
	primary key (id)
);




