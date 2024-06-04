package kr.storyboard.vo;

import java.sql.Date;

public class StoryBoardVO {
	private int s_num;			//스토리 번호
	private String s_title;		//스토리 제목
	private String s_content;	//스토리 내용
	private int s_hit;			//스토리 조회수
	private Date s_rdate;		//스토리 등록일
	private Date s_mdate;		//스토리 수정일
	private String s_ip;		//스토리 IP
	private int s_auth;			//스토리 등급
	private String s_image;		//스토리 이미지 사진
	
	private int mem_num;		//작성자 정보
	private String mem_id;
	private int book_num;		//도서 정보
	private String book_name;
	private int ccount;
	
	
	public int getCcount() {
		return ccount;
	}
	public void setCcount(int ccount) {
		this.ccount = ccount;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public String getS_title() {
		return s_title;
	}
	public void setS_title(String s_title) {
		this.s_title = s_title;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getS_content() {
		return s_content;
	}
	public void setS_content(String s_content) {
		this.s_content = s_content;
	}
	public int getS_hit() {
		return s_hit;
	}
	public void setS_hit(int s_hit) {
		this.s_hit = s_hit;
	}
	public Date getS_rdate() {
		return s_rdate;
	}
	public void setS_rdate(Date s_rdate) {
		this.s_rdate = s_rdate;
	}
	public Date getS_mdate() {
		return s_mdate;
	}
	public void setS_mdate(Date s_mdate) {
		this.s_mdate = s_mdate;
	}
	public String getS_ip() {
		return s_ip;
	}
	public void setS_ip(String s_ip) {
		this.s_ip = s_ip;
	}
	public int getS_auth() {
		return s_auth;
	}
	public void setS_auth(int s_auth) {
		this.s_auth = s_auth;
	}
	public String getS_image() {
		return s_image;
	}
	public void setS_image(String s_image) {
		this.s_image = s_image;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getBook_num() {
		return book_num;
	}
	public void setBook_num(int book_num) {
		this.book_num = book_num;
	}
}
