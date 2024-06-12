package kr.used.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.book.vo.BookVO;
import kr.storyboard.vo.SCommentVO;
import kr.used.vo.UBCommentVO;
import kr.used.vo.UsedVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

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
			DBUtil.executeClose(null, ps, conn);
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
		if(keys.equals("0")) { sub_sql += " where u_num is not null ";}
		else if(keys.equals("1")) {sub_sql += " where u_state = 1 ";}
		else if(keys.equals("2")) {sub_sql += " where u_state = 2";}
		else if(keys.equals("3")) {sub_sql += " where u_state = 3";}
		
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
			DBUtil.executeClose(re, ps, conn);
		}
		return count;
	}
	//글 리스트
	public List<UsedVO> listUsed(int start, int end,String keys, String keyf, String keyw) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		List<UsedVO> list = null;
		String sql=null;
		String sub_sql = "";
		int cnt =0;
		if(keys.equals("0")) { sub_sql += " where u_num is not null ";}
		else if(keys.equals("1")) {sub_sql += " where u_state = 1 ";}
		else if(keys.equals("2")) {sub_sql += " where u_state = 2";}
		else if(keys.equals("3")) {sub_sql += " where u_state = 3";}
		
		if(keyw != null && !"".equals(keyw)) {
			if(keyf.equals("1")) sub_sql += " and ( book_name Like '%' || ? || '%' ) ";
			else if(keyf.equals("2")) sub_sql += " and ( u_title Like '%' || ? || '%' ) ";
			else if(keyf.equals("3")) sub_sql += " and ( mem_id Like '%' || ? || '%' ) ";
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
					UsedDAO dao = new UsedDAO();
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
					used.setCucount(dao.getCommentUsedCount(re.getInt("u_num")));
					
					list.add(used);
				}while(re.next());
				
			
				
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(re, ps, conn);
		}
		return list;
	}
	//내글리스트
	public List<UsedVO> mylistUsed(int start, int end,int memnum) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		List<UsedVO> list = null;
		String sql=null;
	
		int cnt =0;
		
		try {
			conn = DBUtil.getConnection();
			sql="select * from (select rownum alnum, a.* from (select * from (usedbookboard join book using(book_num))join member using(mem_num) where mem_num=? order by u_num desc) a ) where alnum >= ? and alnum <= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(++cnt, memnum);
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
			DBUtil.executeClose(re, ps, conn);
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
			sql="select * from ((usedbookboard join book using(book_num))join member using(mem_num))left outer join member_detail using(mem_num) where u_num=?";
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
				used.setBook_num(re.getInt("book_num"));
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
	//댓글 상세(댓글 수정, 삭제시 작성자 회원 번호 체크 용도로 사용)
	public UBCommentVO getCommentUsed(int uc_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UBCommentVO ub = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM UB_comment WHERE uc_num=?";
			//pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, uc_num);
			//SQL실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ub = new UBCommentVO();
				ub.setUc_num(rs.getInt("uc_num"));
				ub.setMem_num(rs.getInt("mem_num"));
				ub.setUc_content(rs.getString("uc_content"));
				ub.setU_num(rs.getInt("u_num"));
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return ub;
	}
	//댓글작성
	public void insertCommentUsed(UBCommentVO reply) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql="insert into ub_comment(uc_num,uc_content,u_num,mem_num) values(ub_comment_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,reply.getUc_content());
			pstmt.setInt(2, reply.getU_num());
			pstmt.setInt(3, reply.getMem_num());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
			
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	//댓글수정
	public void updateCommentUsed(UBCommentVO ub) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE UB_comment SET uc_content=?,uc_mdate=SYSDATE WHERE uc_num=?";
			//pstmt객체 생성
			pstmt = conn.prepareStatement(sql);
			//?데이터 바인딩
			pstmt.setString(1, ub.getUc_content());
			pstmt.setInt(2, ub.getUc_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//댓글 삭제
	public void deleteCommentUsed(int uc_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM UB_comment WHERE uc_num=?";
			//pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, uc_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//댓글 목록
	public List<UBCommentVO> getListCommentUsed(int start, int end, int u_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UBCommentVO> list = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM ub_comment JOIN member USING(mem_num) "
					+ "WHERE u_num=? ORDER BY uc_num DESC)a) WHERE rnum>=? AND rnum<=?";
			//pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			//데이터 바인딩
			pstmt.setInt(1, u_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//sql문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<UBCommentVO>();
			while(rs.next()) {
				UBCommentVO ub = new UBCommentVO();
				ub.setUc_num(rs.getInt("uc_num"));
				//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
				ub.setUc_rdate(DurationFromNow.getTimeDiffLabel(rs.getString("uc_rdate")));
				if(rs.getString("uc_mdate")!=null) {
					ub.setUc_mdate(DurationFromNow.getTimeDiffLabel(rs.getString("uc_mdate")));					
				}
				ub.setUc_content(StringUtil.useBrNoHTML(rs.getString("uc_content")));
				ub.setUc_num(rs.getInt("uc_num"));
				ub.setMem_num(rs.getInt("mem_num"));//작성자 회원번호
				ub.setMem_id(rs.getString("mem_id"));//작성자 아이디
				
				list.add(ub);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	//댓글 개수
	public int getCommentUsedCount(int u_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0 ;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM UB_comment WHERE u_num=?";
			//pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			//데이터 바인딩
			pstmt.setInt(1, u_num);
			//SQL문 실행
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
	//댓글 목록
	public List<UBCommentVO> getListCommentUsedByUser_num(int start, int end, int user_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UBCommentVO> list = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM ub_comment JOIN member USING(mem_num) "
					+ "WHERE mem_num=? ORDER BY uc_num DESC)a) WHERE rnum>=? AND rnum<=?";
			//pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			//데이터 바인딩
			pstmt.setInt(1, user_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//sql문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<UBCommentVO>();
			while(rs.next()) {
				UBCommentVO ub = new UBCommentVO();
				ub.setUc_num(rs.getInt("uc_num"));
				//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
				ub.setUc_rdate(DurationFromNow.getTimeDiffLabel(rs.getString("uc_rdate")));
				if(rs.getString("uc_mdate")!=null) {
					ub.setUc_mdate(DurationFromNow.getTimeDiffLabel(rs.getString("uc_mdate")));					
				}
				ub.setUc_content(StringUtil.useBrNoHTML(rs.getString("uc_content")));
				ub.setUc_num(rs.getInt("uc_num"));
				ub.setMem_num(rs.getInt("mem_num"));//작성자 회원번호
				ub.setMem_id(rs.getString("mem_id"));//작성자 아이디
				ub.setU_num(rs.getInt("u_num"));//게시글 번호
				
				list.add(ub);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	//댓글 개수
	public int getCommentUsedCountByUser_num(int user_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0 ;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM UB_comment WHERE mem_num=?";
			//pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			//데이터 바인딩
			pstmt.setInt(1, user_num);
			//SQL문 실행
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
	
	//책검색
	public List<BookVO> getCategoryListBook(String keyfield ,int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int cnt =0;
		List<BookVO> list = null;
		if(keyfield != null && !"".equals(keyfield)) {
			sub_sql +=" where book_name Like '%' || ? || '%'";
		}
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM book "+sub_sql +" ORDER BY book_num DESC)a) where rnum between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			if(keyfield != null && !"".equals(keyfield)) {
				pstmt.setString(++cnt, keyfield);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
		
			
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
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		
		return list;
	}
	public int getCategoryListBookCount(String keyfield) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int cnt =0;
		int count =0;
		
		if(keyfield != null && !"".equals(keyfield)) {
			sub_sql +=" where book_name Like '%' || ? || '%'";
		}
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT count(*) FROM book "+sub_sql +" ORDER BY book_num DESC";
			
			pstmt = conn.prepareStatement(sql);
			if(keyfield != null && !"".equals(keyfield)) {
				pstmt.setString(++cnt, keyfield);
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
	//글 개수
	public int getUsedCount(int u_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0 ;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM usedbookboard WHERE u_num=?";
			//pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			//데이터 바인딩
			pstmt.setInt(1, u_num);
			//SQL문 실행
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
	//내 글 개수
		public int myUsedCount(int mem_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0 ;
			try {
				//커넥션 풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM usedbookboard WHERE mem_num=?";
				//pstmt 객체 생성
				pstmt = conn.prepareStatement(sql);
				//데이터 바인딩
				pstmt.setInt(1, mem_num);
				//SQL문 실행
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

}
