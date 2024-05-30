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



CREATE TABLE event_detail (
	ed_num	number	NOT NULL,
	mem_num	number	NOT NULL,
	ed_result	varchar2(20)	NULL,
	e_num	number	NOT NULL,
	constraint ed_pk primary key (ed_num),
	constraint ed_fk1 foreign key (mem_num) references member(mem_num),
	constraint ed_fk2 foreign key (e_num) references eventboard(e_num)
	);
	CREATE TABLE ub_comment (
	uc_num	number		NOT NULL primary key,
	uc_auth	number	DEFAULT 0	NOT NULL,
	uc_content	varchar2(3000)		NOT NULL,
	uc_rdate	date	DEFAULT SYSDATE	 not NULL,
	uc_mdate	date	NULL,
	ub_num number		NOT NULL,
	mem_num	number		NOT NULL,
	constraint ub_fk2 foreign key (u_num) references usedbookboard(u_num)
);

CREATE TABLE story_report (
	sr_num	number		NOT NULL primary key,
	sr_content	varchar2(300)		NOT NULL,
	sr_category	number(1)		NOT NULL,
	mem_num	number		NOT NULL,
	s_num	number		NOT NULL,
	constraint sr_fk1 foreign key (mem_num) references member(mem_num),
	constraint sr_fk2 foreign key (s_num) references storyboard(s_num)
	
	);
CREATE TABLE st_comm_report (
	src_num	number		NOT NULL primary key,
	src_re_content	varchar2(300)		NOT NULL,
	src_category	number(1)		NOT NULL,
	mem_num	number		NOT NULL,
	sc_num	number		NOT NULL,
	constraint sr_fk1 foreign key (mem_num) references member(mem_num),
	constraint sr_fk2 foreign key (sc_num) references story_comment(sc_num)
);
create sequence sr_seq;
create sequence src_seq;

CREATE TABLE used_report (
	ur_num	number		NOT NULL primary key,
	ur_content	varchar2(300)		NOT NULL,
	ur_category	number(1)		NOT NULL,
	mem_num	number		NOT NULL,
	u_num	number		NOT NULL,
	constraint u_fk1 foreign key (mem_num) references member(mem_num),
	constraint u_fk2 foreign key (u_num) references usedbookboard(u_num)
);
create sequence ur_seq;
create sequence urc_seq;

CREATE TABLE used_comm_report (
	urc_num	number		NOT NULL primary key,
	urc_content	varchar2(300)		NOT NULL,
	urc_category	number(1)		NOT NULL,
	mem_num	number		NOT NULL,
	uc_num	number		NOT NULL,
	constraint uc_fk1 foreign key (mem_num) references member(mem_num),
	constraint uc_fk2 foreign key (uc_num) references ub_comment(uc_num)
);

