package kr.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import kr.book.vo.BookVO;
import kr.util.DBUtil;

public class BookDAO {
	private static BookDAO instance = new BookDAO();
		
	public static BookDAO getInstance() {
			return instance;
	}
	private BookDAO() {};
	
	//도서 정보 삽입
	public void InsertBook(BookVO book) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO book (book_num,book_name,author,publisher,publish_date,price,stock,book_category,book_img) VALUES (book_seq.nextval,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBook_name());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getPublish_date());
			pstmt.setInt(5, book.getPrice());
			pstmt.setInt(6, book.getStock());
			pstmt.setInt(7, book.getBook_category());
			pstmt.setString(8,book.getBook_img());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	};
	
	//도서 목록
	public List<BookVO> getListBook(int start, int end, String keyfield, String keyword, int status) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		List<BookVO> list = null;
		String sub_sql = "";
		
		try {
			conn = DBUtil.getConnection();
			
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		
		return list;
	}
}
