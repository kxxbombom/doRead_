create table QnA(
 q_num number not null,
 q_title varchar2(150) not null,
 q_content clob not null,
 q_answer clob,
 q_rdate date default SYSDATE not null,
 q_mdate date,
 q_ip varchar2(40) not null,
 q_auth number(1)default 0 not null,
 q_image varchar2(400),
 mem_num number not null,
 constraint QnA_pk primary key (q_num),
 constraint QnA_fk foreign key (mem_num) references member(mem_num)
);

create sequence QnA_seq;