package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;

public class NewsWrite implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		if(user_num != null && user_auth != 9) {
			request.setAttribute("notice_msg", "잘못된 접근입니다");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		request.setCharacterEncoding("utf-8");
		
		NewsVO news = new NewsVO();
		news.setNews_title(request.getParameter("news_title"));
		news.setNews_content(request.getParameter("news_content"));
		news.setNews_image(FileUtil.createFile(request, "news_image"));
		news.setMem_num(user_num);
		
		NewsDAO dao = NewsDAO.getInstance();
		dao.insertNews(news);
		
		request.setAttribute("notice_msg", "글쓰기 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/news/newsList.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
