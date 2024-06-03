package kr.order.vo;

import java.sql.Date;

public class PointVO {
	private int p_num;
	private int p_detail;
	private int p_point;
	private Date P_rdate;
	private int mem_num;
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getP_detail() {
		return p_detail;
	}
	public void setP_detail(int p_detail) {
		this.p_detail = p_detail;
	}
	public int getP_point() {
		return p_point;
	}
	public void setP_point(int p_point) {
		this.p_point = p_point;
	}
	public Date getP_rdate() {
		return P_rdate;
	}
	public void setP_rdate(Date p_rdate) {
		P_rdate = p_rdate;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
}
