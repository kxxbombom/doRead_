package kr.used.vo;

import java.sql.Date;

public class UsedVO {
	private int u_num;
	private String u_title;
	private String u_content;
	private int u_hit;
	private Date u_rdate;
	private Date u_mdate;
	private String u_image;
	private String u_ip;
	private int u_auth;
	private int book_num;
	private int u_condition;
	private int u_state;
	private int u_price;
	private int mem_num;
	private String book_name;
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	private String id;			//회원아이디
	public int getU_num() {
		return u_num;
	}
	public void setU_num(int u_num) {
		this.u_num = u_num;
	}
	public String getU_title() {
		return u_title;
	}
	public void setU_title(String u_title) {
		this.u_title = u_title;
	}
	public String getU_content() {
		return u_content;
	}
	public void setU_content(String u_content) {
		this.u_content = u_content;
	}
	public int getU_hit() {
		return u_hit;
	}
	public void setU_hit(int u_hit) {
		this.u_hit = u_hit;
	}
	public Date getU_rdate() {
		return u_rdate;
	}
	public void setU_rdate(Date u_rdate) {
		this.u_rdate = u_rdate;
	}
	public Date getU_mdate() {
		return u_mdate;
	}
	public void setU_mdate(Date u_mdate) {
		this.u_mdate = u_mdate;
	}
	public String getU_image() {
		return u_image;
	}
	public void setU_image(String u_image) {
		this.u_image = u_image;
	}
	public String getU_ip() {
		return u_ip;
	}
	public void setU_ip(String u_ip) {
		this.u_ip = u_ip;
	}
	public int getU_auth() {
		return u_auth;
	}
	public void setU_auth(int u_auth) {
		this.u_auth = u_auth;
	}
	public int getBook_num() {
		return book_num;
	}
	public void setBook_num(int book_num) {
		this.book_num = book_num;
	}
	public int getU_condition() {
		return u_condition;
	}
	public void setU_condition(int u_condition) {
		this.u_condition = u_condition;
	}
	public int getU_state() {
		return u_state;
	}
	public void setU_state(int u_state) {
		this.u_state = u_state;
	}
	public int getU_price() {
		return u_price;
	}
	public void setU_price(int u_price) {
		this.u_price = u_price;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	private String photo;

}
