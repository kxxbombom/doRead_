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
			ps.setString(3, used.getU_image());
			ps.setString(4, used.getU_ip());
			ps.setInt(5, used.getBook_num());
			ps.setInt(6, used.getU_condition());
			ps.setInt(7, used.getU_price());
			ps.setInt(8, used.getMem_num() );
			
			ps.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		
	}
	//글 갯수
	public int countUsed(String keys, String keyf,String keyw) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		int count = 0;
		String sub_sql="";
		String sql=null;
		if(keys.equals("0")) sub_sql += " where u_num is not null ";
		else if(keys.equals("1")) sub_sql += " where u_state = 1 ";
		else if(keys.equals("2")) sub_sql += " where u_state = 2";
		else if(keys.equals("3")) sub_sql += " where u_state = 3";
		if(keyw != null && !"".equals(keyw)) {
			if(keyf.equals("1")) sub_sql += " and ( book_name Like '%' || ? || '%' ) ";
			else if(keyf.equals("2")) sub_sql += " and ( u_title Like '%' || ? || '%' ) ";
			else if(keyf.equals("3")) sub_sql += " and ( mem_id Like '%' || ? || '%' ) ";
		}
		try {
			conn = DBUtil.getConnection();
			sql="select count(*) from (usedbookboard join member using(mem_num))join book using(book_num) "+sub_sql;
			ps = conn.prepareStatement(sql);
			if(keyw != null && !"".equals(keyw)) {
				
				ps.setString(1, keyw);
			}
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
	public List<UsedVO> listUsed(int start, int end,String keys, String keyf,String keyw) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		List<UsedVO> list = null;
		String sql=null;
		String sub_sql = "";
		int cnt =0;
		if(keys.equals("0")) sub_sql += " where u_num is not null ";
		else if(keys.equals("1")) sub_sql += " where u_state = 1 ";
		else if(keys.equals("2")) sub_sql += " where u_state = 2";
		else if(keys.equals("3")) sub_sql += " where u_state = 3";
		if(keyw != null && !"".equals(keyw)) {
			if(keyf.equals("1")) sub_sql += " and ( book_name Like '%' || ? || '%' ) ";
			else if(keyf.equals("2")) sub_sql += " and ( u_title Like '%' || ? || '%' ) ";
			else if(keyf.equals("3")) sub_sql += " and ( mem_id Like '%' || ? || '%' )";
		}
		try {
			conn = DBUtil.getConnection();
			sql="select * from (select rownum alnum, a.* from (select * from (usedbookboard join book using(book_num))join member using(mem_num) "+sub_sql +" order by u_num desc) a ) where alnum >= ? and alnum <= ?";
			ps = conn.prepareStatement(sql);
			if(keyw != null && !"".equals(keyw)) {
				
				ps.setString(++cnt, keyw);
			}
			ps.setInt(++cnt, start);
			ps.setInt(++cnt, end);
			re = ps.executeQuery();
			if(re.next()) {
				list = new ArrayList<UsedVO>();
				do{
					UsedVO used = new UsedVO();
					used.setBook_num(re.getInt("book_num"));
					used.setU_num(re.getInt("u_num"));
					used.setU_hit(re.getInt("u_hit"));
					used.setU_state(re.getInt("u_state"));
					used.setU_title(re.getString("u_title"));
					used.setU_content(re.getString("u_content"));
					used.setId(re.getString("mem_id"));
					used.setU_price(re.getInt("u_price"));
					used.setU_auth(re.getInt("u_auth"));
					used.setU_rdate(re.getDate("u_rdate"));
					used.setBook_name(re.getString("book_name"));
					
					list.add(used);
				}while(re.next());
				
			
				
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		return list;
	}
	//글 상세
	public UsedVO detatilUsed(int u_num) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		UsedVO used = null;
		String sql=null;
		try {
			conn = DBUtil.getConnection();
			sql="select * from ((usedbookboard join book using(book_num))join member using(mem_num)) join member_detail using(mem_num) where u_num=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_num);
			re = ps.executeQuery();
			
			if(re.next()) {
				
				used = new UsedVO();
				used.setU_num(re.getInt("u_num"));
				used.setU_hit(re.getInt("u_hit"));
				used.setU_auth(re.getInt("u_auth"));
				used.setU_condition(re.getInt("u_condition"));
				used.setU_state(re.getInt("u_state"));
				used.setU_price(re.getInt("u_price"));
				used.setMem_num(re.getInt("mem_num"));
				used.setU_title(re.getString("u_title"));
				used.setU_content(re.getString("u_content"));
				used.setU_rdate(re.getDate("u_rdate"));
				used.setU_mdate(re.getDate("u_mdate"));
				used.setU_image(re.getString("u_image"));
				used.setU_ip(re.getString("u_ip"));
				used.setBook_name(re.getString("book_name"));
				used.setUser_image(re.getString("mem_photo"));
				used.setId(re.getString("mem_id"));
				
				
				
				
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(re, ps, conn);
		}
		
		
		return used;
		
	}
	//글 수정
	public void updateUsed(UsedVO used) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql="update usedbookboard set u_title=?, u_content=?, u_state=?, u_ip=?, u_image=?, u_price=?, book_num=?, u_condition=?, u_mdate=sysdate  where u_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, used.getU_title());
			pstmt.setString(2, used.getU_content());
			pstmt.setInt(3, used.getU_state());
			pstmt.setString(4, used.getU_ip());
			pstmt.setString(5, used.getU_image());
			pstmt.setInt(6, used.getU_price());
			pstmt.setInt(7, used.getBook_num());
			pstmt.setInt(8, used.getU_condition());
			pstmt.setInt(9, used.getU_num() );
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
			
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	
	// 글 삭제
	public void deleteUsed(int u_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql="delete from usedbookboard where u_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_num);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
			
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		
	}
	//글조회수
	public void hitUsed(int u_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql="update usedbookboard set u_hit=u_hit+1 where u_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_num);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
			
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		
	}
	//댓글작성
	//댓글수정
	//댓글 삭제
	//댓글목록
	//댓글?
	
	//책검색
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
