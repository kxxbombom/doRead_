package kr.event.vo;

public class EventDetailVO {
	private int ed_num;
	private int e_num;
	private int mem_num;
	private String ed_result;
	
	private String e_title;
	private int e_mem_num;
	
	public int getE_mem_num() {
		return e_mem_num;
	}
	public void setE_mem_num(int e_mem_num) {
		this.e_mem_num = e_mem_num;
	}
	public String getE_title() {
		return e_title;
	}
	public void setE_title(String e_title) {
		this.e_title = e_title;
	}
	public int getEd_num() {
		return ed_num;
	}
	public void setEd_num(int ed_num) {
		this.ed_num = ed_num;
	}
	public int getE_num() {
		return e_num;
	}
	public void setE_num(int e_num) {
		this.e_num = e_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getEd_result() {
		return ed_result;
	}
	public void setEd_result(String ed_result) {
		this.ed_result = ed_result;
	}
	
}
