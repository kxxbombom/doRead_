package kr.storyboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.book.vo.BookVO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.DBUtil;
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
					sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM storyboard " + sub_sql
							+" ORDER BY s_num DESC)a) WHERE rnum>=? AND rnum <=?";
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
						sv.setS_num(rs.getInt("s_num"));
						sv.setS_title(StringUtil.useBrNoHTML(rs.getString("s_title")));//html 태그 허용 x
						sv.setS_content(rs.getString("s_content"));
						sv.setS_auth(rs.getInt("s_auth"));
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
			//글 목록,검색 글 목록
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

}
