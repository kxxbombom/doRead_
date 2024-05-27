package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;

public class EventDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		EventVO event = new EventVO();
		EventDAO dao = EventDAO.getInstance();
		event = dao.getEvent(e_num);
		request.setAttribute("event", event);
		return "/WEB-INF/views/event/eventDetail.jsp";
	}

}
