package kr.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.report.vo.ScreportVO;
import kr.report.vo.SreportVO;
import kr.util.DBUtil;

public class ReportDAO {
	private static ReportDAO instance = new ReportDAO();
	public static ReportDAO getInstance() {
		return instance;
	}
	private ReportDAO() {};
	
	//
	public void insertStroyre(SreportVO report) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql=null;
		try {
			conn = DBUtil.getConnection();
			sql="insert into story_report(sr_num,sr_content,sr_category,mem_num,s_num) values(sr_seq.nextval,?,?,?,?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, report.getSr_content());
			ps.setInt(2, report.getSr_category());
			ps.setInt(3, report.getMem_num());
			ps.setInt(4, report.getS_num());
			
			ps.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		}
		
	//
		public void insertSCtroyre(ScreportVO report) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			String sql=null;
			try {
				conn = DBUtil.getConnection();
				sql="insert into st_comm_report(src_num,src_re_content,src_category,mem_num,sc_num) values(sr_seq.nextval,?,?,?,?)";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, report.getSrc_content());
				ps.setInt(2, report.getSrc_category());
				ps.setInt(3, report.getMem_num());
				ps.setInt(4, report.getSc_num());
				
				ps.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.getConnection();
			}
		}
			//신고 수정x
			//관리자 전용 신고 삭제
		public void deleteSre(int num) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			String sql=null;
			try {
				conn = DBUtil.getConnection();
				sql="delete from story_report where sr_num=?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, num);
				
				ps.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.getConnection();
			}
			
		}
		
		public void deleteSrCe(int num) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			String sql=null;
			try {
				conn = DBUtil.getConnection();
				sql="delete from st_comm_report where src_num=?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, num);
				
				ps.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.getConnection();
			}
			
		}
		
		
		
		//신고 목록보기
		//신고 목록 갯수
		
		
	
		}
		

