package kr.event.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;

public class EventMainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EventDAO dao = EventDAO.getInstance();
		List<EventVO> list = dao.getListEvent(1, 10, null, null);
		
		request.setAttribute("List", list);
		return "/WEB-INF/views/event/eventmain.jsp";
	}

}
