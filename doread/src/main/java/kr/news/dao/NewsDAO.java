package kr.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.news.vo.NewsVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class NewsDAO {
	//싱글턴 패턴
	private static NewsDAO instance = new NewsDAO();
		
	public static NewsDAO getInstance() {
		return instance;
	}
		
	private NewsDAO() {}
	
	//뉴스 등록
	public void insertNews(NewsVO news)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO newsboard (news_num,news_title,news_content,news_hit,news_image,mem_num) VALUES (newsboard_seq.nextval,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, news.getNews_title());
			pstmt.setString(2, news.getNews_content());
			pstmt.setInt(3, news.getNews_hit());
			pstmt.setString(4, news.getNews_image());
			pstmt.setInt(5, news.getMem_num());
			
			pstmt.executeUpdate();
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
		}

	}
	//총 글 개수, 검색 개수
	public int getNewsCount(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE news_title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql = "WHERE news_content LIKE '%' || ? || '%'";
			}
			
			sql = "SELECT COUNT(*) FROM newsboard JOIN member USING (mem_num) " + sub_sql;
			
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, keyword);
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
	//뉴스 목록, 검색 목록
	public List<NewsVO> getListNews(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NewsVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE news_title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql = "WHERE news_content LIKE '%' || ? || '%'";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM newsboard JOIN member USING (mem_num) " +sub_sql
					+ "ORDER BY news_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<NewsVO>();
			while(rs.next()) {
				NewsVO news = new NewsVO();
				news.setNews_num(rs.getInt("news_num"));
				news.setNews_title(StringUtil.useNoHTML(rs.getString("news_title")));
				news.setNews_content(rs.getString("news_content"));
				news.setNews_rdate(rs.getDate("news_rdate"));
				news.setMem_id(rs.getString("mem_id"));
				
				list.add(news);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//뉴스 상세
	//조회수 증가
	//파일 삭제 (글 남기고 파일만
	//뉴스 수정
	//뉴스 삭제
}
