package kr.event.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.PagingUtil;

public class EventMainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EventDAO dao = EventDAO.getInstance();
		int count = dao.getEventCount();
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null) pageNum = "1";
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,4,1,request.getContextPath()+"/event/eventmain.do");
		
		List<EventVO> list = dao.getListEvent(page.getStartRow(), page.getEndRow(), null, null);
		
		request.setAttribute("List", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("count", count);
		return "/WEB-INF/views/event/eventmain.jsp";
	}

}
