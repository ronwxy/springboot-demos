create schema if not exists test collate utf8_general_ci;

create table if not exists user
(
	id int auto_increment
		primary key,
	name varchar(20) not null comment '姓名',
	gender char null comment '性别',
	favor json null comment '爱好',
	address json null comment '住址'
);

