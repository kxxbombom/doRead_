package kr.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class AdminDAO {
	
	private static AdminDAO instance = new AdminDAO();
	public static AdminDAO getInstance() {
		return instance;
	}
	private AdminDAO() {};
	//전체 내용 개수, 검색 내용 개수
		public int getMemberCountByAdmin(String keyfield,
				                         String keyword)
		                                   throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";
			int count = 0;
			try {
				//컨넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				
				if(keyword!=null && !"".equals(keyword)) {
					//검색 처리
					if(keyfield.equals("1")) sub_sql += "WHERE mem_id LIKE '%' || ? || '%'";
					else if(keyfield.equals("2")) sub_sql += "WHERE mem_name LIKE '%' || ? || '%'";
					else if(keyfield.equals("3")) sub_sql += "WHERE mem_email LIKE '%' || ? || '%'";
				}
				
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM member LEFT OUTER JOIN "
					+ "member_detail USING(mem_num) " + sub_sql;
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				if(keyword!=null && !"".equals(keyword)) {
					pstmt.setString(1, keyword);
				}
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
		//목록, 검색 목록
		public List<MemberVO> getListMemberByAdmin(
				               int start, int end,
				               String keyfield,String keyword)
				                         throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<MemberVO> list = null;
			String sql = null;
			String sub_sql = "";
			int cnt = 0;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				if(keyword!=null && !"".equals(keyword)) {
					//검색 처리
					if(keyfield.equals("1")) sub_sql += "WHERE mem_id LIKE '%' || ? || '%'";
					else if(keyfield.equals("2")) sub_sql += "WHERE  mem_name LIKE '%' || ? || '%'";
					else if(keyfield.equals("3")) sub_sql += "WHERE mem_email LIKE '%' || ? || '%'";
				}
				
				//SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM member LEFT OUTER JOIN "
					+ "member_detail USING(mem_num) " + sub_sql + " ORDER BY mem_num DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				if(keyword!=null && !"".equals(keyword)) {
					pstmt.setString(++cnt, keyword);
				}
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				//SQL문 실행
				rs = pstmt.executeQuery();
				
				list = new ArrayList<MemberVO>();
				while(rs.next()) {
					MemberVO member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setMem_id(rs.getString("mem_id"));
					member.setMem_auth(rs.getInt("mem_auth"));
					member.setMem_name(rs.getString("mem_name"));
					member.setMem_phone(rs.getString("mem_phone"));
					member.setMem_email(rs.getString("mem_email"));
					member.setMem_rdate(rs.getDate("mem_rdate"));
					
					list.add(member);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}		
			return list;
		}
		
		//auth수정
		public void updateAuth(int auth,int mem_num)
                throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE member SET mem_auth=? WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, auth);
				pstmt.setInt(2, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();			
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
			}
		
}
