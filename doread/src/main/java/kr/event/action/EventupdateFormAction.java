package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;

public class EventupdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Object user_num = 
				(Object)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		Integer mem_num =(Integer)session.getAttribute("user_num");
		
		Integer user_auth = 
				(Integer)session.getAttribute("user_auth");
		if(user_auth != 9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		//관리자가 쓰는 게시글이라 String util안씀
		EventDAO dao = EventDAO.getInstance();
		EventVO event = dao.getEvent(e_num);
		request.setAttribute("event", event);
		request.setAttribute("menu", "event");
		return "/WEB-INF/views/event/eventupdateForm.jsp";
	}

}
