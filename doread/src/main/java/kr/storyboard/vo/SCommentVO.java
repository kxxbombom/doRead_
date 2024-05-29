package kr.storyboard.vo;


public class SCommentVO {
	private int sc_num;
	private String sc_content;
	private int sc_auth;
	private String sc_rdate;
	private String sc_mdate;
	private int mem_num;
	private int s_num;
	
	private String Mem_id;				//작성자 아이디
	
	public String getMem_id() {
		return Mem_id;
	}
	public void setMem_id(String mem_id) {
		Mem_id = mem_id;
	}
	public int getSc_num() {
		return sc_num;
	}
	public void setSc_num(int sc_num) {
		this.sc_num = sc_num;
	}
	public String getSc_content() {
		return sc_content;
	}
	public void setSc_content(String sc_content) {
		this.sc_content = sc_content;
	}
	public int getSc_auth() {
		return sc_auth;
	}
	public void setSc_auth(int sc_auth) {
		this.sc_auth = sc_auth;
	}
	public String getSc_rdate() {
		return sc_rdate;
	}
	public void setSc_rdate(String sc_rdate) {
		this.sc_rdate = sc_rdate;
	}
	public String getSc_mdate() {
		return sc_mdate;
	}
	public void setSc_mdate(String sc_mdate) {
		this.sc_mdate = sc_mdate;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	
}
