package kr.adminster.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.report.dao.ReportDAO;
import kr.report.vo.UcReportVO;

import kr.util.PagingUtil;

public class AdminUClistAction implements Action{
 
	
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
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum="1";
		ReportDAO redao = ReportDAO.getInstance();
		int URcount = redao.countUC();
		
		
		PagingUtil URpage = new PagingUtil(Integer.parseInt(pageNum),URcount,10,10,request.getContextPath()+"/adminster/uclist.do");
		
		List<UcReportVO> list = redao.listUC(URpage.getStartRow(), URpage.getEndRow());
		
		request.setAttribute("page", URpage.getPage());
		request.setAttribute("list", list);
		request.setAttribute("count", URcount);
		
		
		return "/WEB-INF/views/adminster/uclist.jsp";
	}

}
