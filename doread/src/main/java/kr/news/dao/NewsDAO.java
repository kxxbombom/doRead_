package kr.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.news.vo.NewsVO;
import kr.util.DBUtil;

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
		String sql = "";
		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO newsboard (news_num,news_title,news_content,news_hit,news_image,mem_num) VALUES (news_seq.nextval,?,?,?,?,?)";
			
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
	//총 글의 개수, 검색 개수
	//뉴스 목록, 검색 목록
	//뉴스 상세
	//조회수 증가
	//파일 삭제 (글 남기고 파일만
	//뉴스 수정
	//뉴스 삭제
}
