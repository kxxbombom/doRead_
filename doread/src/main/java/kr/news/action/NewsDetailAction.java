package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.StringUtil;

public class NewsDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int news_num = Integer.parseInt(request.getParameter("news_num"));
		
		NewsDAO dao = NewsDAO.getInstance();
		
		//조회수 증가
		dao.updateReadCount(news_num);
		
		NewsVO news = dao.getNews(news_num);
		//HTML 미허용
		news.setNews_title(StringUtil.useNoHTML(news.getNews_title()));
		news.setNews_content(StringUtil.useBrHTML(news.getNews_content()));
		
		return null;
	}

}
