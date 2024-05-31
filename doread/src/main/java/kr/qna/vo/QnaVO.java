package kr.qna.vo;

import java.sql.Date;

import kr.member.vo.MemberVO;

public class QnaVO {
	private int q_num;				//문의글 번호
	private String q_title;			//문의글 제목
	private String q_content;		//문의글 내용
	private String q_answer;		//문의글 답변
	private Date q_rdate;			//문의글 작성일
	private Date q_mdate;			//문의글 수정일
	private String q_ip;			//작성자ip
	private int q_auth;				//문의 유형
	private String q_image;			//문의 사진
	private int mem_num;			//작성자
	
	private String mem_id;
	private int mem_auth;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public int getQ_num() {
		return q_num;
	}
	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}
	public String getQ_title() {
		return q_title;
	}
	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}
	public String getQ_content() {
		return q_content;
	}
	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}
	public String getQ_answer() {
		return q_answer;
	}
	public void setQ_answer(String q_answer) {
		this.q_answer = q_answer;
	}
	public Date getQ_rdate() {
		return q_rdate;
	}
	public void setQ_rdate(Date q_rdate) {
		this.q_rdate = q_rdate;
	}
	public Date getQ_mdate() {
		return q_mdate;
	}
	public void setQ_mdate(Date q_mdate) {
		this.q_mdate = q_mdate;
	}
	public String getQ_ip() {
		return q_ip;
	}
	public void setQ_ip(String q_ip) {
		this.q_ip = q_ip;
	}
	public int getQ_auth() {
		return q_auth;
	}
	public void setQ_auth(int q_auth) {
		this.q_auth = q_auth;
	}
	public String getQ_image() {
		return q_image;
	}
	public void setQ_image(String q_image) {
		this.q_image = q_image;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
}
