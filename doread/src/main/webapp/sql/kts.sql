CREATE TABLE member (
	mem_num	number	NOT NULL,
	mem_id	varchar2(12)	NOT NULL,
	mem_auth	number(1)	NOT NULL
);

CREATE TABLE stroy_report (
	br_num	number	NOT NULL	COMMENT '해당고유번호',
	br_content	varchar2(100)	NOT NULL,
	br_category	number(1)	NOT NULL,
	mem_num	number	NOT NULL,
	b_num	number	NOT NULL
);

CREATE TABLE member_detail (
	mem_num	number	NOT NULL,
	mem_name	varchar2(30)	NOT NULL,
	mem_pw	varchar2(12)	NOT NULL,
	mem_phone	varchar2(15)	NOT NULL,
	mem_email	varchar2(50)	NOT NULL,
	mem_zipcode	varchar2(5)	NOT NULL,
	mem_address1	varchar2(50)	NOT NULL,
	mem_address2	varchar2(50)	NOT NULL,
	mem_rdate	date	NOT NULL	DEFAULT SYSDATE,
	mem_mdate	date	NULL,
	book_category   number(3)	NOT NULL
);

CREATE TABLE st_comm_report (
	brc_num	number	NOT NULL,
	brc_re_content	varchar2(100)	NOT NULL,
	brc_category	number(1)	NOT NULL,
	mem_num	number	NOT NULL,
	bc_num	number	NOT NULL
);

CREATE TABLE book (
	book_num	number	NOT NULL,
	book_name	varchar2(90)	NOT NULL,
	author	varchar(30)	NOT NULL,
	publisher	varchar2(30)	NOT NULL,
	publish_date	varchar2(10)	NOT NULL,
	price	number(8)	NOT NULL,
	stock	number(8)	NOT NULL,
	book_img	varchar2(400)	NOT NULL,
	book_category	number(3)	NOT NULL
);

CREATE TABLE used_report (
	ur_num	number	NOT NULL,
	ur_content	varchar2(100)	NOT NULL,
	ur_category	number(1)	NOT NULL,
	mem_num	number	NOT NULL,
	u_num	number	NOT NULL
);

CREATE TABLE used_comm_report (
	urc_num	number	NOT NULL,
	urc_content	varchar2(100)	NOT NULL,
	urc_category	number(1)	NOT NULL,
	mem_num	number	NOT NULL,
	uc_num	number	NOT NULL
);

CREATE TABLE cart (
	c_num	number	NOT NULL,
	mem_num	number	NOT NULL,
	book_quantity	number(8)	NOT NULL,
	book_num	number	NOT NULL
);

CREATE TABLE like (
	w_num	number	NOT NULL,
	mem_num	number	NOT NULL,
	book_num	number	NOT NULL
);

CREATE TABLE book_order (
	order_num	number	NOT NULL,
	book_price	number(8)	NOT NULL,
	order_date	date	NOT NULL	DEFAULT SYSDATE,
	order_name	varchar2(30)	NOT NULL,
	order_zipcode	varchar2(5)	NOT NULL,
	order_address1	varchar2(40)	NOT NULL,
	order_address2	varchar2(40)	NOT NULL,
	order_payment	number(1)	NOT NULL,
	order_status	number(1)	NOT NULL,
	book_num	number	NOT NULL,
	mem_num	number	NOT NULL
);

CREATE TABLE point (
	p_num	number	NOT NULL,
	p_detail	varchar2	NOT NULL,
	p_point	number	NOT NULL,
	p_rdate	date	NOT NULL,
	mem_num	number	NOT NULL
);
