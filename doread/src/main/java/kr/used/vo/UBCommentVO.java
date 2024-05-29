package kr.used.vo;


public class UBCommentVO {
	private int uc_num;
	private int uc_auth;
	private String uc_content;
	private String uc_rdate;
	private String uc_mdate;
	private int u_num;
	private int mem_num;
	
	private String mem_id;

	public int getUc_num() {
		return uc_num;
	}

	public void setUc_num(int uc_num) {
		this.uc_num = uc_num;
	}

	public int getUc_auth() {
		return uc_auth;
	}

	public void setUc_auth(int uc_auth) {
		this.uc_auth = uc_auth;
	}

	public String getUc_content() {
		return uc_content;
	}

	public void setUc_content(String uc_content) {
		this.uc_content = uc_content;
	}


	public String getUc_rdate() {
		return uc_rdate;
	}

	public void setUc_rdate(String uc_rdate) {
		this.uc_rdate = uc_rdate;
	}

	public String getUc_mdate() {
		return uc_mdate;
	}

	public void setUc_mdate(String uc_mdate) {
		this.uc_mdate = uc_mdate;
	}

	public int getU_num() {
		return u_num;
	}

	public void setU_num(int u_num) {
		this.u_num = u_num;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
}
