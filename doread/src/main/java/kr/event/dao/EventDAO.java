package kr.event.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.admin.dao.AdminDAO;
import kr.book.vo.BookVO;
import kr.event.vo.EventVO;

import kr.util.DBUtil;

public class EventDAO {
	
	private static EventDAO instance = new EventDAO();
	public static EventDAO getInstance() {
		return instance;
	}
	private EventDAO() {};
	//이벤트 글쓰기
	public void eventWrite(EventVO event) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO eventboard (e_num, e_title,e_content, e_hit, e_rdate,e_image,e_item,mem_num,e_deadline) VALUES (event_num.nextval,?,?,?,sysdate,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getE_title());
			pstmt.setString(2, event.getE_content());
			pstmt.setInt(3, 0);
			pstmt.setString(4, event.getE_image());
			pstmt.setString(5, event.getE_item());
			pstmt.setInt(6, event.getMem_num());
			pstmt.setString(7,event.getE_deadline());
			
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	};
	//이벤트 리스트
	public List<EventVO> getListEvent(
		     int start, int end, String keyfield,
		     String keyword)throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<EventVO> list = null;
	String sql = null;
	String sub_sql = "";
	int cnt = 0;
	try {
		//커넥션풀로부터 커넥션 할당
		conn = DBUtil.getConnection();
		
		if(keyword !=null && !"".equals(keyword)) {
			//검색 처리
			if(keyfield.equals("1")) sub_sql += "WHERE e_mem_num is null";
			else if (keyfield.equals("2")) sub_sql += "WHERE e_mem_num is not null";
		}
		
		//SQL문 작성
		//status가 0이면, 1(미표시),2(표시) 모두 호출 -> 관리자용
		//status가 1이면, 2(표시) 호출 -> 사용자용
		sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM "
			+ "(SELECT * FROM eventboard  " + sub_sql
			+ " ORDER BY e_num DESC)a) "
			+ "WHERE rnum >= ? AND rnum <= ?";
		//PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		
		pstmt.setInt(++cnt, start);
		pstmt.setInt(++cnt, end);
		//SQL문 실행
		rs = pstmt.executeQuery();
		list = new ArrayList<EventVO>();
		while(rs.next()) {
			EventVO item = new EventVO();
			item.setE_num(rs.getInt("e_num"));
			item.setE_title(rs.getString("e_title"));
			item.setE_image(rs.getString("e_image"));
			item.setE_rdate(rs.getDate("e_rdate"));
			item.setE_deadline(rs.getString("e_deadline"));
			
			list.add(item);
		}
	}catch(Exception e) {
		throw new Exception(e);
	}finally {
		DBUtil.executeClose(rs, pstmt, conn);
	}		
	return list;
}
	

}
