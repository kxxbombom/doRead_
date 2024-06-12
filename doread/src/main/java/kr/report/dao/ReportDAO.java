package kr.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.report.vo.Countvo;
import kr.report.vo.ScreportVO;
import kr.report.vo.SreportVO;
import kr.report.vo.UcReportVO;
import kr.report.vo.UsedReportVO;
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
			DBUtil.executeClose(null, ps, conn);
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
				DBUtil.executeClose(null, ps, conn);
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
				DBUtil.executeClose(null, ps, conn);
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
				DBUtil.executeClose(null, ps, conn);
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
				DBUtil.executeClose(null, ps, conn);
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
				DBUtil.executeClose(null, ps, conn);
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
		public int countSre(String keyfield,String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			String sub_sql="";
			int count =0;
			
			try {
				conn= DBUtil.getConnection();
				if(keyword!=null && !"".equals(keyword)) {
					
					if(keyfield.equals("1")) sub_sql += "WHERE  s_auth=2";
					
				}
				sql="select count(*) from story_report join storyboard using(s_num) " +sub_sql;
				ps = conn.prepareStatement(sql);
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
		public int countSCre(String keyfield,String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			String sub_sql="";
			int count =0;
			try {
				conn= DBUtil.getConnection();
				if(keyword!=null && !"".equals(keyword)) {
					
					if(keyfield.equals("1")) sub_sql += "WHERE  sc_auth=2";
					
				}
				sql="select count(*) from st_comm_report join story_comment using(sc_num) " + sub_sql ;
				ps = conn.prepareStatement(sql);
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
		//신고 목록보기
		public List<SreportVO> listSre(String keyfield,String keyword,int start,int end) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			String sub_sql="";
			List<SreportVO> list = null;
			
			try {
				conn= DBUtil.getConnection();
				if(keyword!=null && !"".equals(keyword)) {
					
					if(keyfield.equals("1")) sub_sql += "WHERE  s_auth=2";
					
				}
				sql="select * from (select rownum alnum, a.* from(select * from story_report join storyboard using(s_num) "+sub_sql+" order by sr_num desc) a ) where alnum between ? and ? ";
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
		
		public List<ScreportVO> listSCre(String keyfield,String keyword,int start,int end) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re = null;
			String sql=null;
			String sub_sql="";
			List<ScreportVO> list = null;
		
			try {
				conn= DBUtil.getConnection();
				if(keyword!=null && !"".equals(keyword)) {
					
					if(keyfield.equals("1")) sub_sql += " WHERE  sc_auth=2 ";
					
				}
				sql="select * from (select rownum alnum, a.* from(select * from st_comm_report join story_comment using(sc_num) "+sub_sql+" order by src_num desc) a ) where alnum between ? and ? ";
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
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(re, ps, conn);
			}
			return list;
			
			
		}
		
		
		
		
		
		
		
		
		
		
		//-------------------------------------------------------------------------
		
		public void insertUR(UsedReportVO report) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			String sql=null;
			
			try {
				conn = DBUtil.getConnection();
				sql="insert into used_report(ur_num,ur_content,ur_category,mem_num,u_num) values(ur_seq.nextval,?,?,?,?)";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, report.getUr_content());
				ps.setInt(2, report.getUr_category());
				ps.setInt(3, report.getMem_num());
				ps.setInt(4, report.getU_num());
				
				ps.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.getConnection();
			}
			}
			
		//
			public void insertURC(UcReportVO report) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				String sql=null;
				try {
					conn = DBUtil.getConnection();
					sql="insert into used_comm_report(urc_num,urc_content,urc_category,mem_num,uc_num) values(sr_seq.nextval,?,?,?,?)";
					
					ps = conn.prepareStatement(sql);
					ps.setString(1, report.getUrc_content());
					ps.setInt(2, report.getUrc_category());
					ps.setInt(3, report.getMem_num());
					ps.setInt(4, report.getUc_num());
					
					ps.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, ps, conn);
				}
			}
		
	
			
			//신고 중복체크?
			public UsedReportVO checkedU(int u_num, int mem_num) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet re = null;
				String sql=null;
				UsedReportVO report = null;
				try {
					conn = DBUtil.getConnection();
					sql="select * from used_report where u_num=? and mem_num=?";
					
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1, u_num);
					ps.setInt(2, mem_num);
					re=ps.executeQuery();
					
					if(re.next()) {
						report =new UsedReportVO();
						report.setMem_num(mem_num);
						report.setU_num(u_num);
						report.setUr_category(re.getInt("ur_category"));
						report.setUr_content(re.getString("ur_content"));
						report.setUr_num(re.getInt("ur_num"));
						
						
						
					}
					
					
				}catch(Exception e) {
				throw new Exception(e);	
				
				}finally{
					DBUtil.executeClose(re, ps, conn);
				}
				
				
				return report;
				
				
			}
			public UcReportVO checkedUC(int uc_num, int mem_num) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet re = null;
				String sql=null;
				UcReportVO report = null;
				try {
					conn = DBUtil.getConnection();
					sql="select * from used_comm_report where uc_num=? and mem_num=?";
					
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1, uc_num);
					ps.setInt(2, mem_num);
					re=ps.executeQuery();
					
					if(re.next()) {
						report =new UcReportVO();
						report.setMem_num(mem_num);
						report.setUc_num(uc_num);
						report.setUrc_category(re.getInt("urc_category"));
						report.setUrc_content(re.getString("urc_content"));
						report.setUrc_num(re.getInt("Urc_num"));
						
						
						
					}
					
					
				}catch(Exception e) {
				throw new Exception(e);	
				
				}finally{
					DBUtil.executeClose(re, ps, conn);
				}
				
				
				return report;
				
				
			}
			
			//관리자 전용 게시글 등급 수정
			public void updateAuthU(int auth, int num) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				String sql=null;
				try {
					conn = DBUtil.getConnection();
					sql="update usedbookboard set u_auth=?  where u_num=?";
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, auth);
					ps.setInt(2, num);
					
					ps.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, ps, conn);
				}
				
			}
			
			public void updateAuthUC(int auth, int num) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				String sql=null;
				try {
					conn = DBUtil.getConnection();
					sql="update ub_comment set uc_auth=?  where uc_num=?";
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, auth);
					ps.setInt(2, num);
					
					ps.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, ps, conn);
				}
				
			}
			
				//관리자 전용 신고 삭제
			public void deleteUr(int num) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				String sql=null;
				try {
					conn = DBUtil.getConnection();
					sql="delete from used_report where ur_num=?";
					
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1, num);
					
					ps.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, ps, conn);
				}
				
			}
			
			public void deleteUC(int num) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				String sql=null;
				try {
					conn = DBUtil.getConnection();
					sql="delete from used_comm_report where urc_num=?";
					
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1, num);
					
					ps.executeUpdate();
					
				}catch(Exception e) {
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, ps, conn);
				}
				
			}
			
			
			
			
			//신고 목록 갯수
			public int countU(String keyfield,String keyword) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet re = null;
				String sql=null;
				String sub_sql="";
				int count =0;
				try {
					conn= DBUtil.getConnection();
					if(keyword!=null && !"".equals(keyword)) {
						
						if(keyfield.equals("1")) sub_sql += " WHERE  u_auth=2 ";
						
					}
					sql="select count(*) from used_report join usedbookboard using(u_num) " + sub_sql;
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
			public int countUC(String keyfield,String keyword) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet re = null;
				String sql=null;
				String sub_sql="";
				int count =0;
				try {
					conn= DBUtil.getConnection();
					if(keyword!=null && !"".equals(keyword)) {
						
						if(keyfield.equals("1")) sub_sql += " WHERE  uc_auth=2 ";
						
					}
					sql="select count(*) from used_comm_report join ub_comment using(uc_num) " +sub_sql;
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
			public List<UsedReportVO> listU(String keyfield,String keyword,int start,int end) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet re = null;
				String sql=null;
				String sub_sql="";
				List<UsedReportVO> list = null;
				
				try {
					conn= DBUtil.getConnection();
					if(keyword!=null && !"".equals(keyword)) {
						
						if(keyfield.equals("1")) sub_sql += " WHERE  u_auth=2 ";
						
					}
					sql="select * from (select rownum alnum, a.* from(select * from used_report join usedbookboard using(u_num) "+sub_sql+" order by ur_num desc) a ) where alnum between ? and ? ";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, start);
					ps.setInt(2, end);
					re = ps.executeQuery();
					
					if(re.next()) {
						list = new ArrayList<UsedReportVO>();
						do {
							
							UsedReportVO report =new UsedReportVO();
							report.setMem_num(re.getInt("mem_num"));
							report.setU_num(re.getInt("u_num"));
							report.setUr_category(re.getInt("ur_category"));
							report.setUr_content(re.getString("ur_content"));
							report.setUr_num(re.getInt("ur_num"));
							report.setAuth(re.getInt("u_auth"));
							list.add(report);
							
						}while(re.next());
						
						
					}
				}catch(Exception e) {
					
				}finally {
					DBUtil.executeClose(re, ps, conn);
				}
				return list;
				
				
			}
			
			public List<UcReportVO> listUC(String keyfield,String keyword,int start,int end) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet re = null;
				String sql=null;
				String sub_sql="";
				List<UcReportVO> list = null;
			
				try {
					conn= DBUtil.getConnection();
					if(keyword!=null && !"".equals(keyword)) {
						
						if(keyfield.equals("1")) sub_sql += " WHERE  uc_auth=2 ";
						
					}
					sql="select * from (select rownum alnum, a.* from(select * from used_comm_report join ub_comment using(uc_num) "+sub_sql+" order by urc_num desc) a ) where alnum between ? and ? ";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, start);
					ps.setInt(2, end);
					re = ps.executeQuery();
					
					if(re.next()) {
						list = new ArrayList<UcReportVO>();
						do {
							
							UcReportVO report =new UcReportVO();
							report.setMem_num(re.getInt("mem_num"));
							report.setUc_num(re.getInt("uc_num"));
							report.setUrc_category(re.getInt("urc_category"));
							report.setUrc_content(re.getString("urc_content"));
							report.setUrc_num(re.getInt("urc_num"));
							report.setAuth(re.getInt("uc_auth"));
							report.setContent(re.getString("uc_content"));
							report.setU_num(re.getInt("u_num"));
							list.add(report);
							
						}while(re.next());
						
						
					}
				}catch(Exception e) {
					
				}finally {
					DBUtil.executeClose(re, ps, conn);
				}
				return list;
				
				
			}
			
			public List<UcReportVO> listUC5(int start,int end) throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet re = null;
				String sql=null;
				List<UcReportVO> list = null;
			
				try {
					conn= DBUtil.getConnection();
					sql="select count(*),uc_num from (select rownum alnum, a.* from(select * from used_comm_report join ub_comment using(uc_num) order by urc_num desc) a ) where alnum between ? and ? group by uc_num";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, start);
					ps.setInt(2, end);
					re = ps.executeQuery();
					
					if(re.next()) {
						list = new ArrayList<UcReportVO>();
						do {
							
							UcReportVO report =new UcReportVO();
							report.setMem_num(re.getInt("mem_num"));
							report.setUc_num(re.getInt("uc_num"));
							report.setUrc_category(re.getInt("urc_category"));
							report.setUrc_content(re.getString("urc_content"));
							report.setUrc_num(re.getInt("urc_num"));
							report.setAuth(re.getInt("uc_auth"));
							report.setContent(re.getString("uc_content"));
							report.setU_num(re.getInt("u_num"));
							list.add(report);
							
						}while(re.next());
						
						
					}
				}catch(Exception e) {
					
				}finally {
					DBUtil.executeClose(re, ps, conn);
				}
				return list;
				
				
			}
			
			
			public List<Countvo> countFive() throws Exception{
				Connection conn = null;
				PreparedStatement ps = null;
				PreparedStatement ps1 = null;
				PreparedStatement ps2 = null;
				PreparedStatement ps3 = null;
				List<Countvo> list = new ArrayList<Countvo>();
				ResultSet re = null;
				String sql=null;
				
				try {
					conn= DBUtil.getConnection();
					conn.setAutoCommit(false);
					sql="select count(*), uc_num,ub_comment.mem_num from used_comm_report join  ub_comment using(uc_num) group by uc_num,ub_comment.mem_num";
					ps = conn.prepareStatement(sql);
					re = ps.executeQuery();
					
					if(re.next()) {
						
						do {
						Countvo count = new Countvo();
						count.setNumber(re.getInt(2));
						count.setSumcount(re.getInt(1));
						count.setMem_num(re.getInt(3));
						count.setType("uc_num");
						list.add(count);
						}while(re.next());
						
					}
					re.close();
					sql="select count(*), u_num, usedbookboard.mem_num from used_report join usedbookboard using(u_num) group by u_num,usedbookboard.mem_num";
					ps1 = conn.prepareStatement(sql);
					re = ps1.executeQuery();
					
					if(re.next()) {
						
						do {
						Countvo count = new Countvo();
						count.setNumber(re.getInt(2));
						count.setSumcount(re.getInt(1));
						count.setMem_num(re.getInt(3));
						count.setType("u_num");
						list.add(count);
						}while(re.next());
						
					}
					re.close();
					sql="select count(*), s_num,storyboard.mem_num from story_report join storyboard using(s_num) group by s_num,storyboard.mem_num ";
					ps2 = conn.prepareStatement(sql);
					re = ps2.executeQuery();
					
					if(re.next()) {
						
						do {
						Countvo count = new Countvo();
						count.setNumber(re.getInt(2));
						count.setSumcount(re.getInt(1));
						count.setMem_num(re.getInt(3));
						count.setType("s_num");
						list.add(count);
						}while(re.next());  
						
					}
					re.close();
					sql="select count(*), sc_num,story_comment.mem_num from st_comm_report join story_comment using(sc_num) group by sc_num,story_comment.mem_num";
					ps3 = conn.prepareStatement(sql);
					re = ps3.executeQuery();
					
					if(re.next()) {
						
						do {
						Countvo count = new Countvo();
						count.setNumber(re.getInt(2));
						count.setSumcount(re.getInt(1));
						count.setMem_num(re.getInt(3));
						count.setType("sc_num");
						list.add(count);
						}while(re.next());
						
					}
					conn.commit();
				}catch(Exception e) {
					conn.rollback();
					throw new Exception(e);
				}finally {
					DBUtil.executeClose(null, ps3, null);
					DBUtil.executeClose(null, ps2, null);
					DBUtil.executeClose(null, ps1, null);
					DBUtil.executeClose(re, ps, conn);
				}
				return list;
				
				
			}
			
			
			
			
		}
		

