package kr.order.vo;

import java.sql.Date;

public class OrderVO {
	private int order_num;
	private int order_total;
	private Date order_date;
	private Date order_mdate;
	private String receive_name;
	private String receive_zipcode;
	private String receive_address1;
	private String receive_address2;
	private String receive_phone;
	private String order_msg;
	private int order_payment;	//1.계좌입금,2.신용카드
	private int order_status;	//1.결제완료,2.배송시작,3.배송중,4.배송완료,5.주문취소
	private int order_usepoint;
	
	private int mem_num;
	
	private String mem_id;
	private String book_name;
	
	public int getOrder_usepoint() {
		return order_usepoint;
	}
	public void setOrder_usepoint(int order_usepoint) {
		this.order_usepoint = order_usepoint;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getOrder_total() {
		return order_total;
	}
	public void setOrder_total(int order_total) {
		this.order_total = order_total;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getReceive_zipcode() {
		return receive_zipcode;
	}
	public void setReceive_zipcode(String receive_zipcode) {
		this.receive_zipcode = receive_zipcode;
	}
	public String getReceive_address1() {
		return receive_address1;
	}
	public void setReceive_address1(String receive_address1) {
		this.receive_address1 = receive_address1;
	}
	public String getReceive_address2() {
		return receive_address2;
	}
	public Date getOrder_mdate() {
		return order_mdate;
	}
	public void setOrder_mdate(Date order_mdate) {
		this.order_mdate = order_mdate;
	}
	public void setReceive_address2(String receive_address2) {
		this.receive_address2 = receive_address2;
	}
	public String getReceive_phone() {
		return receive_phone;
	}
	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}
	public String getOrder_msg() {
		return order_msg;
	}
	public void setOrder_msg(String order_msg) {
		this.order_msg = order_msg;
	}
	public int getOrder_payment() {
		return order_payment;
	}
	public void setOrder_payment(int order_payment) {
		this.order_payment = order_payment;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
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
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	
	
	
}
