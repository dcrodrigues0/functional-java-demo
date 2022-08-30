create database functional;
use functional;

create table customer (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name varchar(255),
	tier integer
);


create table order_product_relationship (
	order_id INT not null,
	product_id INT not null,
	primary key (order_id, product_id)
);

create table product (
	id INT AUTO_INCREMENT PRIMARY KEY,
	category varchar(255),
	name varchar(255),
	price double
);

create table product_order (
	id INT AUTO_INCREMENT PRIMARY KEY,
	order_date date,
	customer_id bigint
);

alter table order_product_relationship add constraint fk_order_product foreign key (product_id) references product(id);

alter table order_product_relationship add constraint fk_order foreign key (order_id) references product_order(id);

alter table product_order add constraint fk_customer_order foreign key (customer_id) references customer(id);