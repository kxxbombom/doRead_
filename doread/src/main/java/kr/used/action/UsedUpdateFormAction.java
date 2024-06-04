package kr.used.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;

public class UsedUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Object user_num = session.getAttribute("user_num");
		if(user_num == null) {
			
			return "redirect:/member/loginForm.do";
		}
		request.setCharacterEncoding("utf-8");
		Integer mem_num =(Integer)session.getAttribute("user_num");
		int u_num = Integer.parseInt(request.getParameter("u_num"));
		UsedDAO dao = UsedDAO.getInstance();
		UsedVO used = dao.detatilUsed(u_num); 
		if(used.getMem_num() != mem_num) {
			return "/WEB-INF/views/common/notice.jsp";
			
		}
		request.setAttribute("used", used);
		request.setAttribute("menu", "used");
		return "/WEB-INF/views/used/usedupdateForm.jsp";
	}

}
