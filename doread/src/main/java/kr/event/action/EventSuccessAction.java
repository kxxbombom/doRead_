package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventDetailVO;

public class EventSuccessAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Object user_num = 
				(Object)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		Integer mem_num =(Integer)session.getAttribute("user_num");
		int e_num =Integer.parseInt(request.getParameter("e_num"));
		EventDAO dao = EventDAO.getInstance();
		EventDetailVO event = new EventDetailVO();
		event.setE_num(e_num);
		event.setMem_num(mem_num);
		dao.eventDetailWrite(event);
		request.setAttribute("notice_msg", "이벤트에 응모되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/event/detailEvent.do?e_num="+e_num);
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
