package kr.used.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.book.vo.BookVO;
import kr.used.vo.UsedVO;
import kr.util.DBUtil;

public class UsedDAO {
	
	private static UsedDAO instance = new UsedDAO();
	
	public static UsedDAO getInstance() {
		return instance;
	}
	private UsedDAO() {};
	
	//게시판 글 쓰기
	public void insertUsed(UsedVO used) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql=null;
		try {
			conn = DBUtil.getConnection();
			sql="insert into usedbookboard(u_num,u_title,u_content,u_hit,u_rdate,u_image,u_ip,u_auth,book_num,u_condition,u_state,u_price,mem_num)"
					+ " values(usedbookboard_seq.nextval,?,?,0,sysdate,?,?,0,?,?,1,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, used.getU_title());
			ps.setString(2, used.getU_content());
			ps.setInt(3, used.getU_hit());
			ps.setString(4, used.getU_image());
			ps.setString(5, used.getU_ip());
			ps.setInt(6, used.getU_auth());
			ps.setInt(7, used.getBook_num());
			ps.setInt(8, used.getU_condition());
			ps.setInt(9, used.getU_state());
			ps.setInt(10, used.getU_price());
			ps.setInt(11, used.getMem_num() );
			
			ps.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		
	}
	//글 갯수
	public int countUsed(String keyf,String keyw) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		int count = 0;
		String sql=null;
		try {
			conn = DBUtil.getConnection();
			sql="select count(*) from usedbookboard ";
			ps = conn.prepareStatement(sql);
			
			re = ps.executeQuery();
			if(re.next()) {
				
				count = re.getInt(1);
				
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		return count;
	}
	//글 리스트
	public List<UsedVO> listUsed(int start, int end, String keyf,String keyw) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		List<UsedVO> list = null;
		String sql=null;
		try {
			conn = DBUtil.getConnection();
			sql="select count(*) from usedbookboard ";
			ps = conn.prepareStatement(sql);
			
			re = ps.executeQuery();
			if(re.next()) {
				
			
				
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		return list;
	}
	//글 수정 
	// 글 삭제
	//글검색
	//댓글작성
	//댓글수정
	//댓글 삭제
	//댓글목록
	//댓글?
	
	
	public List<BookVO> getCategoryListBook(String keyfield) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		List<BookVO> list = null;
		if(keyfield != null && !"".equals(keyfield)) {
			sub_sql +=" where book_name Like '%' || ? || '%'";
		}
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM book "+sub_sql +" ORDER BY book_num DESC)a)";
			
			pstmt = conn.prepareStatement(sql);
			if(keyfield != null && !"".equals(keyfield)) {
				pstmt.setString(1, keyfield);
			}
			
		
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
			list = new ArrayList<BookVO>();
			
			do {
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
			}while(rs.next());
			
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		
		return list;
	}
	

}
