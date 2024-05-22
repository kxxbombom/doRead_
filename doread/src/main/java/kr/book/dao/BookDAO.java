package kr.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.book.vo.BookVO;
import kr.util.DBUtil;

public class BookDAO {
	private static BookDAO instance = new BookDAO();
		
	public static BookDAO getInstance() {
			return instance;
	}
	private BookDAO() {};
	
	//책 정보 삽입
	public void InsertBook(BookVO book) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		conn = DBUtil.getConnection();
		sql = "INSERT book(book_num,book_name,author,publisher,publish_date,price,stock,book_img,book_category) VALUES (book_seq,?,?,?,?,?,?,?,?)";
		
		
	};
}
