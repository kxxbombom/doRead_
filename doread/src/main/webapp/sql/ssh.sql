CREATE TABLE eventboard (
	e_num	number		NOT NULL primary key,
	e_title	varchar2(150)		NOT NULL,
	e_content	clob		NOT NULL,
	e_hit	number(10)		NOT NULL,
	e_rdate	date	DEFAULT SYSDATE	NOT NULL,
	e_mdate	date		NULL,
	e_image	varchar2(400)		NULL,
	e_item	varchar2(20)		NULL,
	e_deadline	varchar2(10)		NULL,
	mem_num	number		NOT NULL references member(mem_num),
	e_mem_num	number	
	
);

create sequence event_num;