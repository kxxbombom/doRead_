package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventDetailVO;

public class EventPopAction implements Action{

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
		EventDetailVO detail = new EventDetailVO();
		EventDetailVO deaddetail = new EventDetailVO();
		
		
		detail = dao.getEventdetail(mem_num, e_num);
		if(detail != null) {
			request.setAttribute("notice_msg", "이미 참여하신 이벤트 입니다.");
			
		}else {
			deaddetail = dao.getEventdead(e_num);
			if(deaddetail != null) {
				request.setAttribute("notice_msg", "이미 종료된 이벤트입니다.");
			}else {
				EventDetailVO detailevent = new EventDetailVO();
				detailevent.setE_num(e_num);
				detailevent.setMem_num(mem_num);
				dao.eventDetailWrite(detailevent);
				request.setAttribute("notice_msg", "이벤트에 응모되었습니다.");
			}
			
		}
		request.setAttribute("notice_url", request.getContextPath()+"/event/eventmain.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
