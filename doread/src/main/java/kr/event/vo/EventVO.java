package kr.event.vo;

import java.sql.Date;

public class EventVO {
	private int e_num;
	private String e_title;
	private String e_content;
	private int e_hit;
	private Date e_rdate;
	private Date e_mdate;
	private String e_image;
	private String e_item;
	private int e_mem_num;
	private int mem_num;
	private String e_deadline;
	public int getE_num() {
		return e_num;
	}
	public void setE_num(int e_num) {
		this.e_num = e_num;
	}
	public String getE_title() {
		return e_title;
	}
	public void setE_title(String e_title) {
		this.e_title = e_title;
	}
	public String getE_content() {
		return e_content;
	}
	public void setE_content(String e_content) {
		this.e_content = e_content;
	}
	public int getE_hit() {
		return e_hit;
	}
	public void setE_hit(int e_hit) {
		this.e_hit = e_hit;
	}
	public Date getE_rdate() {
		return e_rdate;
	}
	public void setE_rdate(Date e_rdate) {
		this.e_rdate = e_rdate;
	}
	public Date getE_mdate() {
		return e_mdate;
	}
	public void setE_mdate(Date e_mdate) {
		this.e_mdate = e_mdate;
	}
	public String getE_image() {
		return e_image;
	}
	public void setE_image(String e_image) {
		this.e_image = e_image;
	}
	public String getE_item() {
		return e_item;
	}
	public void setE_item(String e_item) {
		this.e_item = e_item;
	}
	public int getE_mem_num() {
		return e_mem_num;
	}
	public void setE_mem_num(int e_mem_num) {
		this.e_mem_num = e_mem_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getE_deadline() {
		return e_deadline;
	}
	public void setE_deadline(String e_deadline) {
		this.e_deadline = e_deadline;
	}
	
}
