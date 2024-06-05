package kr.event.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.event.vo.EventDetailVO;
import kr.event.vo.EventVO;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class EventDAO {
	//이벤트글작성
	private static EventDAO instance = new EventDAO();
	public static EventDAO getInstance() {		return instance;
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
	}
	
	
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
			
			
				//검색 처리
			if(keyfield.equals("1")) sub_sql += "WHERE e_deadline >= sysdate ";
			else if (keyfield.equals("2")) sub_sql += "WHERE  e_deadline < sysdate ";
			else if (keyfield.equals("3")) sub_sql += "WHERE e_mem_num is not null ";
			if(keyword !=null && !"".equals(keyword)) {
				sub_sql += " and  (e_title Like '%' || ? || '%' or e_content Like '%' || ? || '%' )";
		
			}
			
			//SQL문 작성
			
			sql = " SELECT * FROM (SELECT a.*, rownum rnum FROM "
				+ " (SELECT * FROM eventboard " + sub_sql
				+ " ORDER BY e_num DESC)a) "
				+ " WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			if(keyword !=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
				pstmt.setString(++cnt, keyword);
		
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
			list = new ArrayList<EventVO>();
			do{
				EventVO item = new EventVO();
				item.setE_num(rs.getInt("e_num"));
				item.setE_title(rs.getString("e_title"));
				item.setE_image(rs.getString("e_image"));
				item.setE_rdate(rs.getDate("e_rdate"));
				item.setE_deadline(rs.getString("e_deadline"));
				item.setE_mem_num(rs.getInt("e_mem_num"));
				MemberDAO dao= MemberDAO.getInstance();
				MemberVO member =dao.getMember(item.getE_mem_num());
				if(member != null)
				item.setId(member.getMem_id());
				list.add(item);
			}while(rs.next());
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}		
		return list;
	}
		//이벤트 참여자 정보 불러오기
		public List<EventDetailVO> getListMyEvent(
				int start, int end, int user_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<EventDetailVO> list = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql =   " SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ " (SELECT * FROM eventboard e JOIN event_detail ed USING (e_num) WHERE ed.mem_num=?"
						+ " ORDER BY e_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, user_num);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					list = new ArrayList<EventDetailVO>();
					do{
						EventDetailVO item = new EventDetailVO();
						item.setE_num(rs.getInt("e_num"));
						item.setE_title(rs.getString("e_title"));
						item.setMem_num(rs.getInt("mem_num"));
						item.setEd_result(rs.getString("ed_result"));
						item.setE_mem_num(rs.getInt("e_mem_num"));
						
						list.add(item);
					}while(rs.next());
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}		
			return list;
		}
	
		//이벤트 상세 페이지
		public EventVO getEvent(int e_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			
			EventVO event = null;
			try {
				conn = DBUtil.getConnection();
				sql="select * from eventboard where e_num=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, e_num);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					event = new EventVO();
					
					event.setE_title(rs.getString("e_title"));
					event.setE_content(rs.getString("e_content"));
					event.setE_deadline(rs.getString("e_deadline"));
					event.setE_image(rs.getString("e_image"));
					event.setE_num(e_num);
					event.setMem_num(rs.getInt("mem_num"));
					event.setE_rdate(rs.getDate("e_rdate"));
					event.setE_mdate(rs.getDate("e_mdate"));
					event.setE_hit(rs.getInt("e_hit"));
					event.setE_item(rs.getString("e_item"));
					event.setE_mem_num(rs.getInt("e_mem_num"));
					
					
					
				}
			}catch(Exception e) {
				
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
			
			return event;
			
		}
		//이벤트게시글 수카운트 페이지처리
		public int getEventCount(String keyfield,
			     String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql="";
			int count = 0;
			if(keyfield.equals("1")) sub_sql += "WHERE e_deadline >= sysdate ";
			else if (keyfield.equals("2")) sub_sql += "WHERE  e_deadline < sysdate ";
			else if (keyfield.equals("3")) sub_sql += "WHERE e_mem_num is not null ";
			if(keyword !=null && !"".equals(keyword)) {
				sub_sql += " and  (e_title Like '%' || ? || '%' or e_content Like '%' || ? || '%' )";
		
			}
			try {
				conn = DBUtil.getConnection();
				sql="select count(*) from eventboard "+ sub_sql;
				pstmt = conn.prepareStatement(sql);
				
				if(keyword !=null && !"".equals(keyword)) {
					pstmt.setString(1, keyword);
					pstmt.setString(2, keyword);
			
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
		//내가 참여한 이벤트 수
		public int getEventMyCount(int user_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			try {
				conn = DBUtil.getConnection();
				sql="select count(*) from event_detail WHERE mem_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user_num);
				
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
		//이벤트 글수정
		public void updateEvent(EventVO event) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				conn = DBUtil.getConnection();
				sql = "update eventboard set e_title=?,e_content=?,e_mdate=sysdate,e_deadline=?,e_image=?,e_item=? where e_num=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, event.getE_title());
				pstmt.setString(2, event.getE_content());
				pstmt.setString(3,event.getE_deadline());
				pstmt.setString(4, event.getE_image());
				pstmt.setString(5, event.getE_item());
				pstmt.setInt(6, event.getE_num());
				
				
				
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
	 //이벤트 글삭제
				public void deleteEvent(int e_num) throws Exception{
					Connection conn = null;
					
					PreparedStatement pstmt = null;
					PreparedStatement pstmt2 = null;
					String sql = null;
					try {
						conn = DBUtil.getConnection();
						conn.setAutoCommit(false);
						sql = "delete from event_detail where e_num=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, e_num);
						pstmt.executeUpdate();
						sql = "delete from eventboard where e_num=?";
						pstmt2 = conn.prepareStatement(sql);
						pstmt2.setInt(1, e_num);
						pstmt2.executeUpdate();
						
						conn.commit();
					}catch(Exception e) {
						conn.rollback();
						throw new Exception(e);
					}finally {
						DBUtil.executeClose(null, pstmt2, null);
						DBUtil.executeClose(null, pstmt, conn);
					}
				}
		
	 //이벤트 응모
		public void eventDetailWrite(EventDetailVO event) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				conn = DBUtil.getConnection();
				sql = "INSERT INTO event_detail (ed_num, e_num, mem_num) VALUES (event_detail_seq.nextval,?,?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, event.getE_num());
				pstmt.setInt(2, event.getMem_num());
				
				
				
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
	
	//이벤트 응모확인
		public EventDetailVO getEventdetail(int mem_num,int e_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			EventDetailVO event = null;
			
			try {
				conn = DBUtil.getConnection();
				sql="select * from event_detail where e_num=? and mem_num=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, e_num);
				pstmt.setInt(2,mem_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					event = new EventDetailVO();
					event.setEd_num(rs.getInt("ed_num"));
					event.setE_num(rs.getInt("e_num"));
					event.setMem_num(rs.getInt("mem_num"));
					event.setEd_result(rs.getString("ed_result"));
					
					
				}
			}catch(Exception e) {
				
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
			return event;
			
			
		}
		//이벤트 응모 종료확인
				public EventDetailVO getEventdead(int e_num) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = null;
					EventDetailVO event = null;
					
					try {
						conn = DBUtil.getConnection();
						sql="select * from eventboard where e_deadline >= sysdate and e_num=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, e_num);
						
						rs = pstmt.executeQuery();
						if(rs.next()) {
							event = new EventDetailVO();
							
							event.setE_num(rs.getInt("e_num"));
							event.setMem_num(rs.getInt("mem_num"));
							
							
							
						}
					}catch(Exception e) {
						
						throw new Exception(e);
					}finally {
						DBUtil.executeClose(rs, pstmt, conn);
					}
					
					return event;
					
					
				}
		//이벤트 응모기록
		public List<EventDetailVO> getEventdetailUser(int start, int end, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			List<EventDetailVO> list = null;
			
			try {
				conn = DBUtil.getConnection();
				sql="select * from (select rownum alnum , a.* from (select * from event_detail where mem_num=? order by ed_num desc)a ) where alnum between ? and ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_num);
				pstmt.setInt(2,start);
				pstmt.setInt(3,end);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					list = new ArrayList<EventDetailVO>();
					do {
						EventDetailVO event = new EventDetailVO();
						event.setEd_num(rs.getInt("ed_num"));
						event.setE_num(rs.getInt("e_num"));
						event.setMem_num(rs.getInt("mem_num"));
						event.setEd_result(rs.getString("ed_result"));
						list.add(event);
					}while(rs.next());
					
				}
			}catch(Exception e) {
				
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
			return list;
			
			
		}
		//이벤트응모 수카운트 페이지처리
				public int getEvenDetailCount(int mem_num) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String sql = null;
					int count = 0;
					
					try {
						conn = DBUtil.getConnection();
						sql="select count(*) from event_detail where mem_num=? ";
						pstmt = conn.prepareStatement(sql);
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
				//이벤트 당첨자 발표!!
				public void updateEventPresent(EventVO event) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					PreparedStatement pstmt2 = null;
					String sql = null;
					try {
						conn = DBUtil.getConnection();
						conn.setAutoCommit(false);
						sql = "update eventboard set e_mem_num=?,e_deadline=sysdate ,e_content=CONCAT('(종료된이벤트입니다 )',e_content),e_mdate=sysdate where e_num=?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, event.getE_mem_num());
						pstmt.setInt(2, event.getE_num());
						pstmt.executeUpdate();
						sql = "update event_detail set ed_result=? where e_num=? and mem_num=?";
						
						pstmt2 = conn.prepareStatement(sql);
						pstmt2.setString(1, "당첨");
						pstmt2.setInt(2, event.getE_num());
						pstmt2.setInt(3, event.getE_mem_num());
						
					
					
						pstmt2.executeUpdate();
						
						
						conn.commit();
					}catch(Exception e) {
						conn.rollback();
						throw new Exception(e);
					}finally {
						DBUtil.executeClose(null, pstmt2, null);
						DBUtil.executeClose(null, pstmt, conn);
					}
				}

	//조회수
				public void hitEvent(int e_num) throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					String sql = null;
					try {
						conn = DBUtil.getConnection();
						sql = "update eventboard set e_hit=e_hit+1 where e_num=?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, e_num);
						pstmt.executeUpdate();
					}catch(Exception e) {
						throw new Exception(e);
					}finally {
						DBUtil.executeClose(null, pstmt, conn);
					}
				}
}
