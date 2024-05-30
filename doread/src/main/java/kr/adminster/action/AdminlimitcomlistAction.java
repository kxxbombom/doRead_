package kr.adminster.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.report.dao.ReportDAO;
import kr.report.vo.ScreportVO;
import kr.util.PagingUtil;

public class AdminlimitcomlistAction implements Action{

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
		int SRccount = redao.countSCre();
		
		
		PagingUtil SRcpage = new PagingUtil(Integer.parseInt(pageNum),SRccount,10,10,request.getContextPath()+"/adminster/adminreport.do");
		
		List<ScreportVO> list = redao.listSCre(SRcpage.getStartRow(), SRcpage.getEndRow());
		
		request.setAttribute("page", SRcpage.getPage());
		request.setAttribute("list", list);
		request.setAttribute("count", SRccount);
		
		
		return "/WEB-INF/views/adminster/limitsrcomm.jsp";
	}

}
