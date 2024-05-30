package kr.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
			//관리자 전용 게시글 등급 수정
		public void updateAuthSre(int auth, int num) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			String sql=null;
			try {
				conn = DBUtil.getConnection();
				sql="update storyboard set s_auth=?  where s_num=?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, auth);
				ps.setInt(2, num);
				
				ps.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.getConnection();
			}
			
		}
		
		public void updateAuthSCre(int auth, int num) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			String sql=null;
			try {
				conn = DBUtil.getConnection();
				sql="update story_comment set sc_auth=?  where sc_num=?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, auth);
				ps.setInt(2, num);
				
				ps.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.getConnection();
			}
			
		}
		
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
		
		
		//신고 중복체크?
		public SreportVO checkedSre(int s_num, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			SreportVO report = null;
			try {
				conn = DBUtil.getConnection();
				sql="select * from story_report where s_num=? and mem_num=?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, s_num);
				ps.setInt(2, mem_num);
				re=ps.executeQuery();
				
				if(re.next()) {
					report =new SreportVO();
					report.setMem_num(mem_num);
					report.setS_num(s_num);
					report.setSr_category(re.getInt("sr_category"));
					report.setSr_content(re.getString("sr_content"));
					report.setSr_num(re.getInt("sr_num"));
					
					
					
				}
				
				
			}catch(Exception e) {
			throw new Exception(e);	
			
			}finally{
				DBUtil.executeClose(re, ps, conn);
			}
			
			
			return report;
			
			
		}
		public ScreportVO checkedSCre(int sc_num, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			ScreportVO report = null;
			try {
				conn = DBUtil.getConnection();
				sql="select * from st_comm_report where sc_num=? and mem_num=?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, sc_num);
				ps.setInt(2, mem_num);
				re=ps.executeQuery();
				
				if(re.next()) {
					report =new ScreportVO();
					report.setMem_num(mem_num);
					report.setSc_num(sc_num);
					report.setSrc_category(re.getInt("src_category"));
					report.setSrc_content(re.getString("src_re_content"));
					report.setSrc_num(re.getInt("src_num"));
					
					
					
				}
				
				
			}catch(Exception e) {
			throw new Exception(e);	
			
			}finally{
				DBUtil.executeClose(re, ps, conn);
			}
			
			
			return report;
			
			
		}
		//신고 목록 갯수
		public int countSre() throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			int count =0;
			try {
				conn= DBUtil.getConnection();
				sql="select count(*) from story_report";
				ps = conn.prepareStatement(sql);
				re = ps.executeQuery();
				
				if(re.next()) {
					count = re.getInt(1);
					
				}
			}catch(Exception e) {
				
			}finally {
				DBUtil.executeClose(re, ps, conn);
			}
			return count;
			
			
		}
		public int countSCre() throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			int count =0;
			try {
				conn= DBUtil.getConnection();
				sql="select count(*) from st_comm_report";
				ps = conn.prepareStatement(sql);
				re = ps.executeQuery();
				
				if(re.next()) {
					count = re.getInt(1);
					
				}
			}catch(Exception e) {
				
			}finally {
				DBUtil.executeClose(re, ps, conn);
			}
			return count;
			
			
		}
		//신고 목록보기
		public List<SreportVO> listSre(int start,int end) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			List<SreportVO> list = null;
			
			try {
				conn= DBUtil.getConnection();
				sql="select * from (select rownum alnum, a.* from(select * from story_report join storyboard using(s_num) order by sr_num desc) a ) where alnum between ? and ? ";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				re = ps.executeQuery();
				
				if(re.next()) {
					list = new ArrayList<SreportVO>();
					do {
						
						SreportVO report =new SreportVO();
						report.setMem_num(re.getInt("mem_num"));
						report.setS_num(re.getInt("s_num"));
						report.setSr_category(re.getInt("sr_category"));
						report.setSr_content(re.getString("sr_content"));
						report.setSr_num(re.getInt("sr_num"));
						report.setAuth(re.getInt("s_auth"));
						list.add(report);
						
					}while(re.next());
					
					
				}
			}catch(Exception e) {
				
			}finally {
				DBUtil.executeClose(re, ps, conn);
			}
			return list;
			
			
		}
		
		public List<ScreportVO> listSCre(int start,int end) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			List<ScreportVO> list = null;
		
			try {
				conn= DBUtil.getConnection();
				sql="select * from (select rownum alnum, a.* from(select * from st_comm_report join story_comment using(sc_num) order by src_num desc) a ) where alnum between ? and ? ";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				re = ps.executeQuery();
				
				if(re.next()) {
					list = new ArrayList<ScreportVO>();
					do {
						
						ScreportVO report =new ScreportVO();
						report.setMem_num(re.getInt("mem_num"));
						report.setSc_num(re.getInt("sc_num"));
						report.setSrc_category(re.getInt("src_category"));
						report.setSrc_content(re.getString("src_re_content"));
						report.setSrc_num(re.getInt("src_num"));
						report.setAuth(re.getInt("sc_auth"));
						report.setContent(re.getString("sc_content"));
						report.setS_num(re.getInt("s_num"));
						list.add(report);
						
					}while(re.next());
					
					
				}
			}catch(Exception e) {
				
			}finally {
				DBUtil.executeClose(re, ps, conn);
			}
			return list;
			
			
		}
		
	
		}
		

