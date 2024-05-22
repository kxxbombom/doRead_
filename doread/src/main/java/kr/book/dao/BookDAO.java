package kr.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.util.DBUtil;

public class BookDAO {
	private static BookDAO instance = new BookDAO();
		
	public static BookDAO getInstance() {
			return instance;
	}
	private BookDAO() {};
	
	//책 정보 삽입
	public void InsertBook(String book_name, String author, String publisher, String publish_date, int price, int stock, String book_img, String book_category) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		conn = DBUtil.getConnection();
		sql = "INSERT book(book_num,book_name,author,publisher,publish_date,price,stock,book_img,book_category) VALUES (book_seq,?,?,?,?,?,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book_name);
		pstmt.setString(2, author);
		pstmt.setString(3, publisher);
		pstmt.setString(4, publish_date);
		pstmt.setInt(5, price);
		pstmt.setInt(6, stock);
		pstmt.setString(7, book_img);
		pstmt.setString(8, book_category);
		
		pstmt.executeUpdate();
	};
}
