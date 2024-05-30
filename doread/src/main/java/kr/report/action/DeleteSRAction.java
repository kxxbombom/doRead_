package kr.report.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.report.dao.ReportDAO;

public class DeleteSRAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
		
		
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			int num = Integer.parseInt(request.getParameter("num"));
			
			ReportDAO dao =ReportDAO.getInstance();
			if(name.equals("sr_num")) {
				dao.deleteSre(num);
				request.setAttribute("notice_msg", "삭제완료");
				request.setAttribute("notice_url",request.getContextPath()+"/adminster/adminreport.do");
				return "/WEB-INF/views/common/alert_view.jsp";
			
			}else if(name.equals("src_num")) {
				dao.deleteSrCe(num);

				request.setAttribute("notice_msg", "삭제완료");
				request.setAttribute("notice_url",request.getContextPath()+"/adminster/srclist.do");
				return "/WEB-INF/views/common/alert_view.jsp";
				
			}else if(name.equals("ur_num")) {
				dao.deleteUr(num);
				request.setAttribute("notice_msg", "삭제완료");
				request.setAttribute("notice_url",request.getContextPath()+"/adminster/ulist.do");
				return "/WEB-INF/views/common/alert_view.jsp";
				
				
				
				
			}else if(name.equals("urc_num")) {
				dao.deleteUC(num);
				request.setAttribute("notice_msg", "삭제완료");
				request.setAttribute("notice_url",request.getContextPath()+"/adminster/uclimitlist.do");
				return "/WEB-INF/views/common/alert_view.jsp";
				
				
			}
			
		
		
		
			return "/WEB-INF/views/common/notice.jsp";
	}

}
