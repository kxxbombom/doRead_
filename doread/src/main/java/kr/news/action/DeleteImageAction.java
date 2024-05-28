package kr.news.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;

public class DeleteImageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num == null) {
			mapAjax.put("result","logout");
		}else if(user_num != null && user_auth != 9) {
			mapAjax.put("result", "wrongAccess");
		}else {//관리자 로그인
			request.setCharacterEncoding("utf-8");
			
			int news_num = Integer.parseInt(request.getParameter("news_num"));
			NewsDAO dao = NewsDAO.getInstance();
			NewsVO news = dao.getNews(news_num);
			dao.deleteImage(news_num);
			FileUtil.removeFile(request, null);
			mapAjax.put("result", "success");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
