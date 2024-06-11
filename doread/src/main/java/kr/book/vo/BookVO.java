package kr.book.vo;

public class BookVO {
	private int book_num; 			//책 번호
	private String book_name; 		//책 이름
	private String author; 			//저자
	private String publisher; 		//출판사
	private String publish_date; 	//출간일
	private int price; 				//가격
	private int stock; 				//재고
	private String book_img;		//책 이미지
	private int book_category;		//책 분야
	private int book_auth; //0판매중 1판매중단
	private String book_content;
	public String getBook_content() {
		return book_content;
	}
	public void setBook_content(String book_content) {
		this.book_content = book_content;
	}
	public int getBook_auth() {
		return book_auth;
	}
	public void setBook_auth(int book_auth) {
		this.book_auth = book_auth;
	}
	public int getBook_num() {
		return book_num;
	}
	public void setBook_num(int book_num) {
		this.book_num = book_num;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getBook_img() {
		return book_img;
	}
	public void setBook_img(String book_img) {
		this.book_img = book_img;
	}
	public int getBook_category() {
		return book_category;
	}
	public void setBook_category(int book_category) {
		this.book_category = book_category;
	}

	
}
