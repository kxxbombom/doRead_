package kr.used.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.used.dao.UsedDAO;

public class UsedWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Object user_num = session.getAttribute("user_num");
		if(user_num == null) {
			
			return "redirect:/member/loginForm.do";
		}
		
		
		request.setAttribute("menu", "used");
		return "/WEB-INF/views/used/usedWriteForm.jsp";
	}

}
