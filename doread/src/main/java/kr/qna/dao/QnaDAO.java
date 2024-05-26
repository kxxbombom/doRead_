package kr.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.qna.vo.QnaVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class QnaDAO {
	//싱글턴 패턴
		private static QnaDAO instance = new QnaDAO();
		
		public static QnaDAO getInstance() {
			return instance;
		}
		
		private QnaDAO() {}
		
		//글등록
		public void insertBoard(QnaVO qna)throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션 풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO qna (q_num,q_title,q_content,q_ip,q_auth,q_image,mem_num) "
						+ "VALUES (qna_seq.nextval,?,?,?,?,?,?)";
				//pstmt 객체 생성
				pstmt = conn.prepareStatement(sql);
				//데이터 바인딩	
				pstmt.setString(1, qna.getQ_title());
				pstmt.setString(2, qna.getQ_content());
				pstmt.setString(3, qna.getQ_ip());
				pstmt.setInt(4, qna.getQ_auth());
				pstmt.setString(5, qna.getQ_image());
				pstmt.setInt(6, qna.getMem_num());
				//SQL문 실행
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//총 글 개수,검색 개수
		public int getQnaCount(String keyfield, String keyword)throws Exception{
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
					if(keyfield.equals("1"))sub_sql +=" WHERE q_title LIKE '%' || ? || '%'";
					else if(keyfield.equals("2"))sub_sql +=" WHERE q_content LIKE '%' || ? || '%'";
				}
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM qna" + sub_sql; 
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
		public List<QnaVO> getListQna(int start, int end, String keyfield, String keyword)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<QnaVO> list = null;
			String sql = null;
			String sub_sql = "";
			int cnt = 0;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				if(keyword!=null && !"".equals(keyword)) {
					if(keyfield.equals("1")) sub_sql +="WHERE q_title LIKE '%' || ? || '%'";
					else if(keyfield.equals("2")) sub_sql +="WHERE q_content LIKE '%' || ? || '%'";
				}
				
				//SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM QnA " + sub_sql
						+" ORDER BY q_num DESC)a) WHERE rnum>=? AND rnum <=?";
				pstmt = conn.prepareStatement(sql);
				if(keyword!=null&&!"".equals(keyword)) {
					pstmt.setString(++cnt, keyword);
				}
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				
				//sql문 실행
				rs= pstmt.executeQuery();
				list = new ArrayList<QnaVO>();
				while(rs.next()) {
					QnaVO qna = new QnaVO();
					qna.setQ_num(rs.getInt("q_num"));
					qna.setQ_title(StringUtil.useBrNoHTML(rs.getString("q_title")));//html 태그 허용 x
					qna.setQ_content(rs.getString("q_content"));
					qna.setQ_auth(rs.getInt("q_auth"));
					qna.setQ_image(rs.getString("q_image"));
					qna.setQ_rdate(rs.getDate("q_rdate"));
					
					
					list.add(qna);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		//QNA 상세
		public QnaVO getQna(int q_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			QnaVO qna = null;
			String sql=null;
			try {
				//커넥션 풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//sql문 작성
				//(주의) 회원 탈퇴를 하게되면 member_detail의 레코드를 지우기 때문에 조인시 데이터 누락 방지를 위해 outer join을 사용
				sql= "SELECT * FROM qna JOIN member USING(mem_num) LEFT OUTER JOIN member_detail USING(mem_num) "
						+ "WHERE q_num=?";
				//pstmt 객체 생성
				pstmt = conn.prepareStatement(sql);
				//데이터바인딩
				pstmt.setInt(1, q_num);
				//sql문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					qna = new QnaVO();
					qna.setQ_num(rs.getInt("q_num"));
					qna.setQ_title(rs.getString("q_title"));
					qna.setQ_content(rs.getString("q_content"));
					qna.setQ_rdate(rs.getDate("q_rdate"));
					qna.setQ_mdate(rs.getDate("q_mdate"));
					qna.setQ_image(rs.getString("q_image"));
					qna.setQ_answer(rs.getString("q_answer"));
	
					//로그인한 회원번호와 조건 체크를 해야하기 때문에 mem_num필요
					qna.setMem_num(rs.getInt("mem_num"));
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
			return qna;
		}
}
