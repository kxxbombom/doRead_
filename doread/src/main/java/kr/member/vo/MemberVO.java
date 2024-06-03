package kr.member.vo;

import java.sql.Date; 

public class MemberVO {
	private int mem_num;
	private String mem_name;
	private String mem_id;
	private String mem_pw;
	private String mem_phone;
	private String mem_email;
	private String mem_zipcode;
	private String mem_address1;
	private String mem_address2;
	private int mem_auth;
	private Date mem_rdate;
	private Date mem_mdate;
	private String mem_photo;
	private Integer book_category;
	private Integer book_category2;
	private Integer book_category3;
	
	public Integer getBook_category2() {
		return book_category2;
	}

	public void setBook_category2(Integer book_category2) {
		this.book_category2 = book_category2;
	}

	public Integer getBook_category3() {
		return book_category3;
	}

	public void setBook_category3(Integer book_category3) {
		this.book_category3 = book_category3;
	}

	public boolean isCheckedPassword(String userpw) {
		if(mem_auth > 1 && mem_pw.equals(userpw)) {
			return true;
		}
		return false;
	}
	
	public String getMem_photo() {
		return mem_photo;
	}

	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}

	
		
	public Integer getBook_category() {
		return book_category;
	}

	public void setBook_category(Integer book_category) {
		this.book_category = book_category;
	}


	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_zipcode() {
		return mem_zipcode;
	}

	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}

	public String getMem_address1() {
		return mem_address1;
	}

	public void setMem_address1(String mem_address1) {
		this.mem_address1 = mem_address1;
	}

	public String getMem_address2() {
		return mem_address2;
	}

	public void setMem_address2(String mem_address2) {
		this.mem_address2 = mem_address2;
	}

	public int getMem_auth() {
		return mem_auth;
	}

	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}

	public Date getMem_rdate() {
		return mem_rdate;
	}

	public void setMem_rdate(Date mem_rdate) {
		this.mem_rdate = mem_rdate;
	}

	public Date getMem_mdate() {
		return mem_mdate;
	}

	public void setMem_mdate(Date mem_mdate) {
		this.mem_mdate = mem_mdate;
	}
}