package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventDetailVO;
import kr.event.vo.EventVO;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class EventPresentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth = 
				(Integer)session.getAttribute("user_auth");
		if(user_auth != 9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		int e_num= Integer.parseInt(request.getParameter("e_num"));
		int e_mem_num= Integer.parseInt(request.getParameter("e_mem_num"));
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO  vo = dao.getMember(e_mem_num);
		if(vo == null) {
			request.setAttribute("notice_msg", "회원번호가 아닙니다");
			request.setAttribute("notice_url", request.getContextPath()+"/event/eventmain.do");
			return "/WEB-INF/views/common/alert_view.jsp";
			
		}else {
			 
			EventDAO eventdao = EventDAO.getInstance();
			EventDetailVO check = eventdao.getEventdetail(e_mem_num, e_num);
			if(check == null) {
				request.setAttribute("notice_msg", "이벤트에 응모한 회원번호가 아닙니다.");
				request.setAttribute("notice_url", request.getContextPath()+"/event/eventmain.do");
				return "/WEB-INF/views/common/alert_view.jsp";
				
			}
			EventVO eventvo = new EventVO();
			eventvo.setE_num(e_num);
			eventvo.setE_mem_num(e_mem_num);
			eventdao.updateEventPresent(eventvo);
			
		}
		return "redirect:/event/detailEvent.do?e_num="+e_num;
	}

}
