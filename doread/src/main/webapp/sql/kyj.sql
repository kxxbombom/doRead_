create table book(
	book_num number not null,
	book_name varchar2(90) not null,
	autor varchar2(30) not null,
	publisher varchar2(30) not null,
	publish_date varchar2(30) not null,
	price number(8) not null,
	stock number(8) not null,
	book_img varchar2(400) not null,
	book_category number(3) not null
);

create sequence book_seq;