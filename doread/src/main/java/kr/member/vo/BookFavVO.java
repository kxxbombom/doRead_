package kr.member.vo;

public class BookFavVO {
	private int book_num;
	private int mem_num;
	
	public BookFavVO() {

		
	}
	public BookFavVO(int book_num, int mem_num) {
		this.book_num = book_num;
		this.mem_num = mem_num;
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
	
}
