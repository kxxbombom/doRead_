package kr.cart.vo;

import java.sql.Date;

import kr.book.vo.BookVO;

public class CartVO {
	private int c_num;
	private int c_quantity;		//상품 주문수량
	private int book_num;
	private int mem_num;
	private Date c_rdate;
	private int sub_total; //동일 상품 구매금액
	
	private BookVO bookVO;
	
	
	public BookVO getBookVO() {
		return bookVO;
	}
	public void setBookVO(BookVO bookVO) {
		this.bookVO = bookVO;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public int getC_quantity() {
		return c_quantity;
	}
	public void setC_quantity(int c_quantity) {
		this.c_quantity = c_quantity;
	}
	public int getBook_num() {
		return book_num;
	}
	public void setBook_num(int book_num) {
		this.book_num = book_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public Date getC_rdate() {
		return c_rdate;
	}
	public void setC_rdate(Date c_rdate) {
		this.c_rdate = c_rdate;
	}
	public int getSub_total() {
		return sub_total;
	}
	public void setSub_total(int sub_total) {
		this.sub_total = sub_total;
	}
	
	
	
}
