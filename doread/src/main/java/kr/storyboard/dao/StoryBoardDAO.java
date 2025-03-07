package kr.storyboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.book.vo.BookVO;
import kr.storyboard.vo.SCommentVO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class StoryBoardDAO {
			//싱글턴 패턴
			private static StoryBoardDAO instance = new StoryBoardDAO();
			
			public static StoryBoardDAO getInstance() {
			return instance;
			}
			
			private StoryBoardDAO() {}
	
			//총 글 개수,검색 개수
			public int getStoryBoardCount(String keyfield, String keyword)throws Exception{
				Connection conn=null;
				PreparedStatement pstmt =null;
				ResultSet rs = null;
				String sql = null;
				String sub_sql = "";
				int count = 0;
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//검색 처리
					if(keyword!=null && !"".equals(keyword)) {
						if(keyfield.equals("1"))sub_sql +=" WHERE s_title LIKE '%' || ? || '%'";
						else if(keyfield.equals("2"))sub_sql +=" WHERE s_content LIKE '%' || ? || '%'";
					}
					//SQL문 작성
					sql = "SELECT COUNT(*) FROM storyboard" + sub_sql; 
					//pstmt객체 생성
					pstmt = conn.prepareStatement(sql);
					if(keyword!=null&&!"".equals(keyword)) {
						pstmt.setString(1, keyword);
					}
					rs = pstmt.executeQuery();
					if(rs.next()) {
						count=rs.getInt(1);
					}
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(rs, pstmt, conn);
				}
				
				return count;
			}
			//내 글 개수
			public int myStoryBoardCount(int mem_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				int count = 0 ;
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "SELECT COUNT(*) FROM storyboard WHERE mem_num=?";
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
			//글 목록,검색 글 목록
			public List<StoryBoardVO> getListStoryBoard(int start, int end, String keyfield, String keyword)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<StoryBoardVO> list = null;
				String sql = null;
				String sub_sql = "";
				int cnt = 0;
				try {
					//커넥션풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					if(keyword!=null && !"".equals(keyword)) {
						if(keyfield.equals("1")) sub_sql +="WHERE s_title LIKE '%' || ? || '%'";
						else if(keyfield.equals("2")) sub_sql +="WHERE s_content LIKE '%' || ? || '%'";
					}
					
					//SQL문 작성
					sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM storyboard JOIN member USING(mem_num) " + sub_sql
							+" ORDER BY s_num DESC)a) WHERE rnum>=? AND rnum <=? AND s_auth = 0";
					pstmt = conn.prepareStatement(sql);
					if(keyword!=null&&!"".equals(keyword)) {
						pstmt.setString(++cnt, keyword);
					}
					pstmt.setInt(++cnt, start);
					pstmt.setInt(++cnt, end);
					
					//sql문 실행
					rs= pstmt.executeQuery();
					list = new ArrayList<StoryBoardVO>();
					while(rs.next()) {
						StoryBoardVO sv = new StoryBoardVO();
						StoryBoardDAO dao = new StoryBoardDAO();
						sv.setS_num(rs.getInt("s_num"));
						sv.setS_title(StringUtil.useBrNoHTML(rs.getString("s_title")));//html 태그 허용 x
						sv.setS_content(rs.getString("s_content"));
						sv.setS_auth(rs.getInt("s_auth"));
						sv.setS_hit(rs.getInt("s_hit"));
						sv.setS_image(rs.getString("s_image"));
						sv.setS_rdate(rs.getDate("s_rdate"));
						sv.setBook_num(rs.getInt("book_num"));
						sv.setMem_id(rs.getString("mem_id"));
						sv.setCcount(dao.getCommentStoryCount(rs.getInt("s_num")));
						
						list.add(sv);
					}
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(rs, pstmt, conn);
				}
				return list;
			}
			//글등록
			public void insertStoryBoard(StoryBoardVO sb)throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = null;
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "INSERT INTO storyboard (s_num,s_title,s_content,s_ip,s_image,mem_num,book_num) "
							+ "VALUES (storyboard_seq.nextval,?,?,?,?,?,?)";
					//pstmt 객체 생성
					pstmt = conn.prepareStatement(sql);
					//데이터 바인딩	
					pstmt.setString(1, sb.getS_title());
					pstmt.setString(2, sb.getS_content());
					pstmt.setString(3, sb.getS_ip());
					pstmt.setString(4, sb.getS_image());
					pstmt.setInt(5, sb.getMem_num());
					pstmt.setInt(6, sb.getBook_num());
					//SQL문 실행
					pstmt.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt, conn);
				}
			}
			//도서 목록
			public List<BookVO> getListBookByStory() throws Exception {
			    Connection conn = null;
			    PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    List<BookVO> list = new ArrayList<>();
			    String sql = "SELECT * FROM book ORDER BY book_name ASC";

			    try {
			        // 커넥션풀로부터 커넥션 할당
			        conn = DBUtil.getConnection();
			        
			        // SQL문 작성 및 실행
			        pstmt = conn.prepareStatement(sql);
			        rs = pstmt.executeQuery();

			        // 결과 처리
			        while (rs.next()) {
			            BookVO book = new BookVO();
			            book.setBook_num(rs.getInt("book_num"));
			            book.setBook_name(rs.getString("book_name"));
			            list.add(book);
			        }

			    } catch (Exception e) {
			        throw new Exception(e);
			    } finally {
			        DBUtil.executeClose(rs, pstmt, conn);
			    }

			    return list;
			}
			//story 상세
			public StoryBoardVO getStoryBoard(int s_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt =null;
				ResultSet rs = null;
				StoryBoardVO sb = null;
				String sql=null;
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//sql문 작성
					//(주의) 회원 탈퇴를 하게되면 member_detail의 레코드를 지우기 때문에 조인시 데이터 누락 방지를 위해 outer join을 사용
					sql= "SELECT * FROM ((storyboard JOIN member USING(mem_num) FULL OUTER JOIN book USING(book_num)) LEFT OUTER join member_detail using(mem_num)) "
							+ "WHERE s_num=?";
					//pstmt 객체 생성
					pstmt = conn.prepareStatement(sql);
					//데이터바인딩
					pstmt.setInt(1, s_num);
					//sql문 실행
					rs = pstmt.executeQuery();
					if(rs.next()) {
						sb = new StoryBoardVO();
						sb.setS_num(rs.getInt("s_num"));
						sb.setS_title(rs.getString("s_title"));
						sb.setS_content(rs.getString("s_content"));
						sb.setS_rdate(rs.getDate("s_rdate"));
						sb.setS_mdate(rs.getDate("s_mdate"));
						sb.setS_image(rs.getString("s_image"));
						sb.setS_hit(rs.getInt("s_hit"));
						//로그인한 회원번호와 조건 체크를 해야하기 때문에 mem_num필요
						sb.setMem_num(rs.getInt("mem_num"));
						sb.setMem_id(rs.getString("mem_id"));
						sb.setBook_num(rs.getInt("book_num"));
						sb.setBook_name(rs.getString("book_name"));
						sb.setMem_photo(rs.getString("mem_photo"));
					}
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(rs, pstmt, conn);
				}
				
				return sb;
			}
			//글 수정
			public void updateStory(StoryBoardVO sb)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = null;
				String sub_sql = "";
				int cnt = 0;
				try {
					//커넥션 풀에서 커넥션 할당
					conn = DBUtil.getConnection();
					if(sb.getS_image()!=null && !"".equals(sb.getS_image())) { 
						sub_sql+=",s_image=?";
					}
					//SQL문 작성
					sql = "UPDATE storyboard SET s_title=?,s_content=?,s_mdate=SYSDATE,s_ip=?,book_num=?" + sub_sql + " WHERE s_num=?";
					//pstmt 객체 생성
					pstmt = conn.prepareStatement(sql);
					//데이터바인딩
					pstmt.setString(++cnt, sb.getS_title());
					pstmt.setString(++cnt, sb.getS_content());
					pstmt.setString(++cnt, sb.getS_ip());
					pstmt.setInt(++cnt, sb.getBook_num());
					if(sb.getS_image()!=null && !"".equals(sb.getS_image())) {
						pstmt.setString(++cnt, sb.getS_image());				
					}
					pstmt.setInt(++cnt, sb.getS_num());				
					//SQL문 실행
					pstmt.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt, conn);
				}
			}
			//글 삭제
			public void deleteStory(int s_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt2 = null;
				PreparedStatement pstmt3 = null;
				PreparedStatement pstmt4 = null;
				String sql = null;
				try {
					//커넥션 풀에서 커넥션 할당
					conn = DBUtil.getConnection();
					//오토커밋 해제
					conn.setAutoCommit(false);
					
					//SQL문 작성
					sql = "DELETE FROM story_report WHERE s_num=?";
					//pstmt 객체 생성
					pstmt2 = conn.prepareStatement(sql);
					//?에 데이터 바인딩
					pstmt2.setInt(1, s_num);
					pstmt2.executeUpdate();
					sql = "DELETE FROM st_comm_report WHERE sc_num in(select sc_num from story_comment where s_num=? )";
					pstmt4 = conn.prepareStatement(sql);
					pstmt4.setInt(1, s_num);
					pstmt4.executeUpdate();
					sql = "DELETE FROM story_comment WHERE s_num=?";
					pstmt3 = conn.prepareStatement(sql);
					pstmt3.setInt(1, s_num);
					pstmt3.executeUpdate();
					//부모글 삭제
					sql="DELETE FROM storyboard WHERE s_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, s_num);
					pstmt.executeUpdate();
					
					//예외발생 없이 정상적으로 SQL문 실행
					conn.commit();
					
				}catch(Exception e) {
					//예외 발생
					conn.rollback();
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt4, null);
					DBUtil.executeClose(null, pstmt3, null);
					DBUtil.executeClose(null, pstmt2, null);
					DBUtil.executeClose(null, pstmt, conn);
				}
				
			}
			//조회수 증가
			public void updateReadcount(int s_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = null;
				try {
					//커넥션풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql ="UPDATE storyboard SET s_hit=s_hit+1 WHERE s_num=?";
					//pstmt  객체 생성
					pstmt = conn.prepareStatement(sql);
					//데이터 바인딩
					pstmt.setInt(1, s_num);
					//SQL 문 실행
					pstmt.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt, conn);
				}
			}
			//댓글 등록
			public void insertCommentStory(SCommentVO SComment) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = null;
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "INSERT INTO story_comment (sc_num,sc_content,mem_num,s_num) VALUES "
							+ "(story_comment_seq.nextval,?,?,?)";
					//pstmt객체 생성
					pstmt = conn.prepareStatement(sql);
					//데이터 바인딩
					pstmt.setString(1,SComment.getSc_content());
					pstmt.setInt(2,SComment.getMem_num());
					pstmt.setInt(3,SComment.getS_num());
					//SQL문실행
					pstmt.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt, conn);
				}
			}
			//댓글 목록
			public List<SCommentVO> getListCommentStory(int start, int end, int s_num) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<SCommentVO> list = null;
				String sql = null;
				
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM story_comment JOIN member USING(mem_num) "
							+ "WHERE s_num=? ORDER BY sc_num DESC)a) WHERE rnum>=? AND rnum<=?";
					//pstmt 객체 생성
					pstmt = conn.prepareStatement(sql);
					//데이터 바인딩
					pstmt.setInt(1, s_num);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					//sql문 실행
					rs = pstmt.executeQuery();
					list = new ArrayList<SCommentVO>();
					while(rs.next()) {
						SCommentVO comment = new SCommentVO();
						comment.setSc_num(rs.getInt("sc_num"));
						//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
						comment.setSc_rdate(DurationFromNow.getTimeDiffLabel(rs.getString("sc_rdate")));
						if(rs.getString("sc_mdate")!=null) {
							comment.setSc_mdate(DurationFromNow.getTimeDiffLabel(rs.getString("sc_mdate")));					
						}
						comment.setSc_auth(rs.getInt("sc_auth"));
						comment.setSc_content(StringUtil.useBrNoHTML(rs.getString("sc_content")));
						comment.setSc_num(rs.getInt("sc_num"));
						comment.setMem_num(rs.getInt("mem_num"));//작성자 회원번호
						comment.setMem_id(rs.getString("mem_id"));//작성자 아이디
						
						list.add(comment);
					}
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(rs, pstmt, conn);
				}
				
				return list;
			}
			//댓글 개수
			public int getCommentStoryCountByUser_num(int user_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				int count = 0 ;
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "SELECT COUNT(*) FROM story_comment WHERE mem_num=?";
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
			//댓글 목록
			public List<SCommentVO> getListCommentStoryByUser_num(int start, int end, int user_num) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<SCommentVO> list = null;
				String sql = null;
				
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM story_comment JOIN member USING(mem_num) "
							+ "WHERE mem_num=? ORDER BY sc_num DESC)a) WHERE rnum>=? AND rnum<=?";
					//pstmt 객체 생성
					pstmt = conn.prepareStatement(sql);
					//데이터 바인딩
					pstmt.setInt(1, user_num);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					//sql문 실행
					rs = pstmt.executeQuery();
					list = new ArrayList<SCommentVO>();
					while(rs.next()) {
						SCommentVO comment = new SCommentVO();
						comment.setSc_num(rs.getInt("sc_num"));
						//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
						comment.setSc_rdate(DurationFromNow.getTimeDiffLabel(rs.getString("sc_rdate")));
						if(rs.getString("sc_mdate")!=null) {
							comment.setSc_mdate(DurationFromNow.getTimeDiffLabel(rs.getString("sc_mdate")));					
						}
						comment.setSc_content(StringUtil.useBrNoHTML(rs.getString("sc_content")));
						comment.setSc_num(rs.getInt("sc_num"));
						comment.setMem_num(rs.getInt("mem_num"));//작성자 회원번호
						comment.setMem_id(rs.getString("mem_id"));//작성자 아이디
						comment.setS_num(rs.getInt("s_num"));//story 번호
						
						list.add(comment);
					}
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(rs, pstmt, conn);
				}
				
				return list;
			}
			//댓글 개수
			public int getCommentStoryCount(int s_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				int count = 0 ;
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "SELECT COUNT(*) FROM story_comment WHERE s_num=?";
					//pstmt 객체 생성
					pstmt = conn.prepareStatement(sql);
					//데이터 바인딩
					pstmt.setInt(1, s_num);
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
			//댓글 상세(댓글 수정, 삭제시 작성자 회원 번호 체크 용도로 사용)
			public SCommentVO getCommentStory(int sc_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				SCommentVO sc = null;
				String sql = null;
				try {
					//커넥션 풀로부터 커넥션을 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "SELECT * FROM story_comment WHERE sc_num=?";
					//pstmt 객체 생성
					pstmt = conn.prepareStatement(sql);
					//?에 데이터 바인딩
					pstmt.setInt(1, sc_num);
					//SQL실행
					rs = pstmt.executeQuery();
					if(rs.next()) {
						sc = new SCommentVO();
						sc.setSc_num(rs.getInt("sc_num"));
						sc.setMem_num(rs.getInt("mem_num"));
						sc.setSc_content(rs.getString("sc_content"));
						sc.setS_num(rs.getInt("s_num"));
					}
					
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(rs, pstmt, conn);
				}
				
				return sc;
			}
			//댓글 수정
			public void updateCommentStory(SCommentVO sc) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = null;
				try {
					//커넥션 풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					//SQL문 작성
					sql = "UPDATE story_comment SET sc_content=?,sc_mdate=SYSDATE WHERE sc_num=?";
					//pstmt객체 생성
					pstmt = conn.prepareStatement(sql);
					//?데이터 바인딩
					pstmt.setString(1, sc.getSc_content());
					pstmt.setInt(2, sc.getSc_num());
					//SQL문 실행
					pstmt.executeUpdate();
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt, conn);
				}
			}
			//댓글 삭제
			public void deleteCommentStory(int sc_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt2 = null;
				String sql = null;
				try {
					//커넥션 풀로부터 커넥션을 할당
					conn = DBUtil.getConnection();
					conn.setAutoCommit(false);
					//SQL문 작성
					sql = "DELETE FROM st_comm_report WHERE sc_num=?";
					//pstmt 객체 생성
					pstmt2 = conn.prepareStatement(sql);
					//?에 데이터 바인딩
					pstmt2.setInt(1, sc_num);
					pstmt2.executeUpdate();
					sql = "DELETE FROM story_comment WHERE sc_num=?";
					//pstmt 객체 생성
					pstmt = conn.prepareStatement(sql);
					//?에 데이터 바인딩
					pstmt.setInt(1, sc_num);
					//SQL문 실행
					pstmt.executeUpdate();
					conn.commit();
				}catch(Exception e) {
					conn.rollback();
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, pstmt2, null);
					DBUtil.executeClose(null, pstmt, conn);
				}
			}
			//my글 목록,검색 글 목록
			public List<StoryBoardVO> getListMyStoryBoard(int start, int end, String keyfield, String keyword, int user_num)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<StoryBoardVO> list = null;
				String sql = null;
				String sub_sql = "";
				int cnt = 0;
				try {
					//커넥션풀로부터 커넥션 할당
					conn = DBUtil.getConnection();
					if(keyword!=null && !"".equals(keyword)) {
						if(keyfield.equals("1")) sub_sql +="and s_title LIKE '%' || ? || '%'";
						else if(keyfield.equals("2")) sub_sql +="and s_content LIKE '%' || ? || '%'";
					}
					
					//SQL문 작성
					sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM storyboard where mem_num=? " + sub_sql
							+" ORDER BY s_num DESC)a) WHERE rnum>=? AND rnum <=? " ;
					pstmt = conn.prepareStatement(sql);
					if(keyword!=null&&!"".equals(keyword)) {
						pstmt.setString(++cnt, keyword);
					}
					pstmt.setInt(++cnt, user_num);
					pstmt.setInt(++cnt, start);
					pstmt.setInt(++cnt, end);
					
					//sql문 실행
					rs= pstmt.executeQuery();
					list = new ArrayList<StoryBoardVO>();
					while(rs.next()) {
						StoryBoardVO sv = new StoryBoardVO();
						sv.setS_num(rs.getInt("s_num"));
						sv.setS_title(StringUtil.useBrNoHTML(rs.getString("s_title")));//html 태그 허용 x
						sv.setS_content(rs.getString("s_content"));
						sv.setS_auth(rs.getInt("s_auth"));
						sv.setS_hit(rs.getInt("s_hit"));
						sv.setS_image(rs.getString("s_image"));
						sv.setS_rdate(rs.getDate("s_rdate"));
						sv.setBook_num(rs.getInt("book_num"));
						
						list.add(sv);
					}
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(rs, pstmt, conn);
				}
				return list;
			}
}
