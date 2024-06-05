package kr.event.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventDetailVO;
import kr.event.vo.EventVO;
import kr.util.PagingUtil;

public class ParticipateEventListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth= (Integer)session.getAttribute("user_auth");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword= request.getParameter("keyword");
		
		EventDAO dao = EventDAO.getInstance();
		int count = dao.getEventMyCount(user_num);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count, 10,10,"participateEventList.do");
		List<EventDetailVO> list = null;
		if(count > 0) {
			list = dao.getListMyEvent(page.getStartRow(), page.getEndRow(),user_num);
		}
		request.setAttribute("user_num", user_num);
		request.setAttribute("user_auth", user_auth);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		// JSP 경로 반환
		return "/WEB-INF/views/event/participateEventList.jsp";
	}

}
