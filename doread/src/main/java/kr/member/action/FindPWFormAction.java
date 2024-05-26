package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class FindPWFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String numParam = request.getParameter("num");
		Integer num;
		if (numParam != null && !numParam.isEmpty()) {
			num = Integer.parseInt(numParam);
		} else {
			num = null;
		}
		
		if(id == null || num == null) {
			request.setAttribute("notice_msg", "잘못된 접근입니다");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
			
		request.setAttribute("id", id);
		request.setAttribute("num", num);
		
		return "/WEB-INF/views/member/findPWForm.jsp";
	}

}
