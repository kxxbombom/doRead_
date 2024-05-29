--회원
create table member(
 mem_num number not null,
 mem_id varchar2(12) unique not null,
 mem_auth number(1) not null,
 constraint member_pk primary key (mem_num)
);
create sequence member_seq;

--회원상세
create table member_detail{
 mem_num number not null,
 mem_name varchar2(30) not null,
 mem_pw varchar2(12) not null,
 mem_phone varchar2(15) not null,
 mem_email varchar2(50) not null,
 mem_zipcode varchar2(5) not null,
 mem_address1 varchar2(50) not null,
 mem_address2 varchar2(50) not null,
 mem_date date default sysdate not null,
 mem_mdate date,
 book_category varchar2(12),
 mem_photo varchar(400),
 constraint member_detail_pk primary key (mem_num),
 constraint member_detail_fk foreign key (mem_num) references zmember (mem_num)
};

--도서 정보 저장
create table book(
 book_num number not null,
 book_name varchar2(90) not null,
 author varchar2(30) not null,
 publisher varchar2(30) not null,
 publish_date varchar2(10) not null,
 price number(8) not null,
 stock number(8) not null,
 book_img varchar2(400) not null,
 book_category number(3) not null,
 constraint book_pk primary key (book_num)
);

 
 --뉴스게시판
 CREATE TABLE newsboard(
 news_num number NOT NULL,
 news_title varchar2(150) NOT NULL,
 news_content clob NOT NULL,
 news_hit number(10) NOT NULL,
 news_rdate Date DEFAULT SYSDATE NOT NULL,
 news_mdate date,
 news_image varchar2(400),
 mem_num number	NOT NULL,
 constraint newboard_pk primary key (news_num),
 constraint newsboard_fk (mem_num) references member_detail (mem_num)
);
 
create sequence newsboard_seq;


--장바구니
CREATE TABLE cart (
 c_num number NOT NULL,
 c_quantity number(8) NOT NULL,
 book_num number NOT NULL,
 mem_num number NOT NULL,
 c_rdate date default sysdate not null,
 constraint cart_pk primary key (c_num),
 constraint cart_fk1 foreign key (book_num) references book (book_num),
 constraint cart_fk2 foreign key (mem_num) references member (mem_num)
);

create sequence cart_seq;













