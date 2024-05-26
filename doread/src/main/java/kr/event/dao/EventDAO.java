package kr.event.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

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

}
