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

public class AdminEventMembList implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
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
				int e_num = Integer.parseInt(request.getParameter("e_num"));
				EventDAO dao = EventDAO.getInstance();
				dao.hitEvent(e_num);
				EventVO event = new EventVO();
				
				event = dao.getEvent(e_num);
				
				int count = dao.getAdminEvenDetailCount(e_num);
				String pageNum = request.getParameter("pageNum");
				
				if(pageNum == null) pageNum = "1";
				
				PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,5,5,request.getContextPath()+"/event/detailAdminEvent.do");
				List<EventDetailVO> list = dao.getEventdetailAdmin(page.getStartRow(),page.getEndRow(), e_num);
				
				request.setAttribute("event", event);
				request.setAttribute("menu", "event");
				request.setAttribute("list", list);
				request.setAttribute("count", count);
				request.setAttribute("page", page.getPage());
				return "/WEB-INF/views/event/admineventDetail.jsp";
	}

}
