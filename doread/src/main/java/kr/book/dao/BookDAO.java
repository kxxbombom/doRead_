package kr.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.book.vo.BookVO;
import kr.member.vo.BookFavVO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

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
			sql = "INSERT INTO book (book_num,book_name,author,publisher,publish_date,price,stock,book_category,book_img,book_auth) VALUES (book_seq.nextval,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBook_name());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getPublish_date());
			pstmt.setInt(5, book.getPrice());
			pstmt.setInt(6, book.getStock());
			pstmt.setInt(7, book.getBook_category());
			pstmt.setString(8,book.getBook_img());
			pstmt.setInt(9,book.getBook_auth());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	};
	
	//도서 총 개수, 검색 도서 총 개수
	public int getBookCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE book_name LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE author LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "WHERE publisher LIKE '%' || ? || '%'";
			}
			
			sql = "SELECT COUNT(*) FROM book "+sub_sql;
			
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, keyword);
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//카테고리별 도서 개수
	public int getCategoryBookCount(String keyfield, String keyword, int book_category) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += " book_name LIKE '%' || ? || '%' AND";
				else if(keyfield.equals("2")) sub_sql += " author LIKE '%' || ? || '%' AND";
				else if(keyfield.equals("3")) sub_sql += " publisher LIKE '%' || ? || '%' AND";
			}
			
			sql = "SELECT COUNT(*) FROM book WHERE"+sub_sql+" book_category=?";
			
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, book_category);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//도서 목록- 책 카테고리 메뉴 클릭
	public List<BookVO> getListBook(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<BookVO> list = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE book_name LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE author LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "WHERE publisher LIKE '%' || ? || '%'";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM book "+sub_sql+" ORDER BY book_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			if((keyword!=null)&&!"".equals(keyword)){
				pstmt.setString(++cnt,keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
		
			
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
				book.setBook_auth(rs.getInt("book_auth"));
				
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
		String sub_sql = "";
		List<BookVO> list = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "AND book_name LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "AND author LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "AND publisher LIKE '%' || ? || '%'";
			}
			
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM book WHERE book_category=? "+sub_sql+" ORDER BY book_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(++cnt, book_category);
			
			if((keyword!=null)&&!"".equals(keyword)){
				pstmt.setString(++cnt,keyword);
			}
			
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
	
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
				book.setBook_auth(rs.getInt("book_auth"));
				
				list.add(book);
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		
		return list;
	}
	
	//도서 정보 수정
	public void UpdateBook(String book_name, String author, 
			String publisher, String publish_date, 
			int price, int stock, String book_img, 
			int book_category, int book_auth, int book_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE book SET book_name=?, author=?, publisher=?, publish_date=?, "
					+ "price=?, stock=?, book_img=?, book_category=? book_auth=? "
					+ "WHERE book_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book_name);
			pstmt.setString(2, author);
			pstmt.setString(3, publisher);
			pstmt.setString(4, publish_date);
			pstmt.setInt(5,price);
			pstmt.setInt(6,stock);
			pstmt.setString(7, book_img);
			pstmt.setInt(8, book_category);
			pstmt.setInt(9, book_auth);
			pstmt.setInt(10, book_num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//해당 도서 리뷰 개수 구하기
	public int getBookStoryCount(int book_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int s_count = 0;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM storyboard JOIN book USING (book_num) WHERE book_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				s_count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		return s_count;
	}
	
	//도서 상세정보
	public BookVO getBookDetail(int book_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO book = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book WHERE book_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				book = new BookVO();
				book.setBook_num(rs.getInt("book_num"));
				book.setBook_name(rs.getString("book_name"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setStock(rs.getInt("stock"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublish_date(rs.getString("publish_date"));
				book.setBook_img(rs.getString("book_img"));
				book.setBook_category(rs.getInt("book_category"));
				book.setBook_auth(rs.getInt("book_auth"));
			}
			return book;
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//좋아요 등록
		public void insertFav(BookFavVO favVO)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO book_fav (w_num,book_num,mem_num) VALUES(bookfav_seq.nextval,?,?)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, favVO.getBook_num());
				pstmt.setInt(2, favVO.getMem_num());
				//SQL문 작성
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//좋아요 개수
		public int selectFavCount(int book_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM book_fav WHERE book_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, book_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e) {
				
			}finally{
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
		//회원번호와 게시물 번호를 이용한 좋아요 정보
		public BookFavVO selectFav(BookFavVO favVO)
										throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BookFavVO fav = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM book_fav WHERE book_num=? AND mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, favVO.getBook_num());
				pstmt.setInt(2, favVO.getMem_num());
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					fav = new BookFavVO();
					fav.setBook_num(rs.getInt("book_num"));
					fav.setMem_num(rs.getInt("mem_num"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return fav;
		}
		
		//회원 찜목록
		public List<BookVO> getListBookFav(int start, int end, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			List<BookVO> list = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT * FROM book b JOIN book_fav f USING(book_num) WHERE f.mem_num=? "
						+ "ORDER BY book_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,mem_num);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<BookVO>();
				while(rs.next()) {
					BookVO book = new BookVO();
					book.setBook_num(rs.getInt("book_num"));
					book.setBook_name(rs.getString("book_name"));
					book.setAuthor(rs.getString("author"));
					book.setPrice(rs.getInt("price"));
					book.setPublisher(rs.getString("publisher"));
					book.setPublish_date(rs.getString("publish_date"));
					
					list.add(book);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		};
		
		//(회원이 게시물을 호출했을 때 좋아요 선택 여부 표시)
		//좋아요 삭제
		public void deleteFav(BookFavVO favVO)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "DELETE FROM book_fav WHERE book_num=? AND mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, favVO.getBook_num());
				pstmt.setInt(2, favVO.getMem_num());
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		//도서 리뷰 불러오기
		public List<StoryBoardVO> getBookStory(int book_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			List<StoryBoardVO> list = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM storyboard WHERE book_num=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, book_num);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<StoryBoardVO>();
				while(rs.next()) {
					StoryBoardVO story = new StoryBoardVO();
					story.setS_num(rs.getInt("s_num"));
					story.setS_title(rs.getString("s_title"));
					story.setS_hit(rs.getInt("s_hit"));
					story.setS_rdate(rs.getDate("s_rdate"));
					story.setMem_num(rs.getInt("mem_num"));
					
					list.add(story);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
	
	//회원 선호 카테고리 도서 개수
	public int getRecommendBookCount(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(book_num) FROM book b RIGHT OUTER JOIN member_detail m "
					+ "ON b.book_category=m.book_category OR "
					+ "b.book_category=m.book_category2 OR "
					+ "b.book_category=m.book_category3 WHERE mem_num=?";

			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//회원 선호 카테고리 도서 불러오기
	public List<BookVO> getRecommendBook(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<BookVO> recommend = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book b RIGHT OUTER JOIN member_detail m "
					+ "ON b.book_category=m.book_category OR "
					+ "b.book_category=m.book_category2 OR "
					+ "b.book_category=m.book_category3 WHERE mem_num=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			
			recommend = new ArrayList<BookVO>();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO book= new BookVO();
				book.setBook_num(rs.getInt("book_num"));
				book.setBook_name(rs.getString("book_name"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setBook_img(rs.getString("book_img"));
				book.setBook_auth(rs.getInt("book_auth"));
				book.setBook_category(rs.getInt("book_category"));
				
				recommend.add(book);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return recommend;
	}
		
}
