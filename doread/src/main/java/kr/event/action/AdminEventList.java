package kr.event.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.PagingUtil;

public class AdminEventList implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Object user_num = 
				(Object)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth = 
				(Integer)session.getAttribute("user_auth");
		if(user_auth != 9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}

		String keyfeild = request.getParameter("sel");
		String keyvalue= request.getParameter("eventsearch");
		if(keyfeild == null) keyfeild = "1";
		EventDAO dao = EventDAO.getInstance();
		int count = dao.getEventCount(keyfeild, keyvalue);
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null) pageNum = "1";
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,4,5,request.getContextPath()+"/event/eventmain.do");
		
		List<EventVO> list = dao.getListEvent(page.getStartRow(), page.getEndRow(),  keyfeild, keyvalue);
		int check = Integer.parseInt(keyfeild);
		request.setAttribute("List", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("count", count);
		request.setAttribute("check", check);
		request.setAttribute("menu", "event");
		return "/WEB-INF/views/event/adminEventList.jsp";
	}

}
