package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.StringUtil;

public class NewsUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null) {
			return "redirect:member/loginForm.do";
		}
		if(user_num != null && user_auth != 9) {
			request.setAttribute("notice_msg", "잘못된 접근입니다");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		//관리자 로그인
		int news_num = Integer.parseInt(request.getParameter("news_num"));
		
		NewsDAO dao = NewsDAO.getInstance();
		NewsVO news = dao.getNews(news_num);
		
		news.setNews_title(StringUtil.parseQuot(news.getNews_title()));
		
		request.setAttribute("news", news);
		
		return "/WEB-INF/views/news/NewsUpdateForm.jsp";
	}

}
