package kr.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	//도서 목록- 책 카테고리 메뉴 클릭
	public List<BookVO> getListBook(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<BookVO> list = null;

		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM book ORDER BY book_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
		
			
			rs = pstmt.executeQuery();
			list = new ArrayList<BookVO>();
			
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBook_num(rs.getInt("book_num"));
				book.setBook_name(rs.getString("book_name"));
				book.setAuthor(rs.getString("Author"));
				book.setPrice(rs.getInt("price"));
				book.setStock(rs.getInt("stock"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublish_date(rs.getString("publish_date"));
				book.setBook_img(rs.getString("book_img"));
				book.setBook_category(rs.getInt("book_category"));
				
				list.add(book);
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		
		return list;
	}
	
	//도서 목록- 카테고리별
	public List<BookVO> getCategoryListBook(int start, int end, String keyfield, String keyword, int book_category) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<BookVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM book WHERE book_category=? ORDER BY book_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_category);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
		
			
			rs = pstmt.executeQuery();
			list = new ArrayList<BookVO>();
			
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBook_num(rs.getInt("book_num"));
				book.setBook_name(rs.getString("book_name"));
				book.setAuthor(rs.getString("Author"));
				book.setPrice(rs.getInt("price"));
				book.setStock(rs.getInt("stock"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublish_date(rs.getString("publish_date"));
				book.setBook_img(rs.getString("book_img"));
				book.setBook_category(rs.getInt("book_category"));
				
				list.add(book);
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		
		return list;
	}
	
	
}
