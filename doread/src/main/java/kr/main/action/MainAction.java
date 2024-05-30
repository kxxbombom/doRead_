package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//책표시
		BookDAO dao = BookDAO.getInstance();
		List<BookVO> bookList = dao.getListBook(1, 8, null, null); 
		
		request.setAttribute("bookList", bookList);
		
		//이벤트 표시
		EventDAO dao2 = EventDAO.getInstance();
		List<EventVO> eventList = dao2.getListEvent(1, 8, "1", null); 
		
		request.setAttribute("eventList", eventList);
		
		
		//JSP 경로 반환
		return "/WEB-INF/views/main/main.jsp";
	}
}






