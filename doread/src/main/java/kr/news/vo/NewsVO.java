package kr.news.vo;

import java.sql.Date;

public class NewsVO {
	private int news_num;
	private String news_title;
	private String news_content;
	private int news_hit;
	private Date news_rdate;
	private Date news_mdate;
	private String news_image;
	private int mem_num;
	
	private String mem_id;
	private String mem_photo;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
	public int getNews_num() {
		return news_num;
	}
	public void setNews_num(int news_num) {
		this.news_num = news_num;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public int getNews_hit() {
		return news_hit;
	}
	public void setNews_hit(int news_hit) {
		this.news_hit = news_hit;
	}
	public Date getNews_rdate() {
		return news_rdate;
	}
	public void setNews_rdate(Date news_rdate) {
		this.news_rdate = news_rdate;
	}
	public Date getNews_mdate() {
		return news_mdate;
	}
	public void setNews_mdate(Date news_mdate) {
		this.news_mdate = news_mdate;
	}
	public String getNews_image() {
		return news_image;
	}
	public void setNews_image(String news_image) {
		this.news_image = news_image;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	
}
