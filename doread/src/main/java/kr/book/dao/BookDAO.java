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
	
	//도서 목록
	public List<BookVO> getListBook(int start, int end, String keyfield, String keyword, int book_category) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<BookVO> list = null;
		String sub_sql = "";
		
		try {
			conn = DBUtil.getConnection();
			
			if(book_category==1) sub_sql="WHERE book_category=1";
			else if(book_category==2) sub_sql="WHERE book_category=2";
			else if(book_category==3) sub_sql="WHERE book_category=3";
			else if(book_category==4) sub_sql="WHERE book_category=4";
			else if(book_category==5) sub_sql="WHERE book_category=5";
			else if(book_category==6) sub_sql="WHERE book_category=6";
			else if(book_category==7) sub_sql="WHERE book_category=7";
			else if(book_category==8) sub_sql="WHERE book_category=8";
			else if(book_category==9) sub_sql="WHERE book_category=9";
			else if(book_category==10) sub_sql="WHERE book_category=10";
			else if(book_category==11) sub_sql="WHERE book_category=11";
			else sub_sql="";
		
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM book " +sub_sql+ " ORDER BY book_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			
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
}
