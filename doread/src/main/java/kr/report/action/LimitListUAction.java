package kr.report.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.report.dao.ReportDAO;
import kr.report.vo.ScreportVO;
import kr.report.vo.SreportVO;
import kr.report.vo.UcReportVO;
import kr.report.vo.UsedReportVO;
import kr.util.PagingUtil;

public class LimitListUAction implements Action{

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
		int URcount = redao.countU("1","1");
		
		
		PagingUtil URpage = new PagingUtil(Integer.parseInt(pageNum),URcount,URcount,10,request.getContextPath()+"/adminster/ulist.do");
		
		List<UsedReportVO> list = redao.listU("1","1",URpage.getStartRow(), URpage.getEndRow());
		
		request.setAttribute("page", URpage.getPage());
		request.setAttribute("list", list);
		request.setAttribute("count", URcount);
		
		String pageNum1 = request.getParameter("pageNum1");
		if(pageNum1 == null) pageNum1="1";
		
		int UR1count = redao.countUC("1","1");
		
		
		PagingUtil UR1page = new PagingUtil(null,null,Integer.parseInt(pageNum1),UR1count,UR1count,10,request.getContextPath()+"/adminster/uclist.do",null,"1");
		
		List<UcReportVO> list1 = redao.listUC("1","1",URpage.getStartRow(), URpage.getEndRow());
		
		request.setAttribute("page1", UR1page.getPage());
		request.setAttribute("list1", list1);
		request.setAttribute("count1", UR1count);
		
		
		String pageNum2 = request.getParameter("pageNum2");
		if(pageNum2 == null) pageNum2="1";
		
		int SRcount = redao.countSre("1","1");
		PagingUtil SRpage = new PagingUtil(null,null,Integer.parseInt(pageNum2),SRcount,SRcount,10,request.getContextPath()+"/adminster/adminreport.do",null,"2");
		
		List<SreportVO> list2 = redao.listSre("1","1",SRpage.getStartRow(), SRpage.getEndRow());
		
		request.setAttribute("page2", SRpage.getPage());
		request.setAttribute("list2", list2);
		request.setAttribute("count2", SRcount);
		
		String pageNum3 = request.getParameter("pageNum3");
		if(pageNum3 == null) pageNum3="1";
	
		int SRccount = redao.countSCre("1","1");
		
		
		PagingUtil SRcpage = new PagingUtil(null,null,Integer.parseInt(pageNum3),SRccount,SRccount,10,request.getContextPath()+"/adminster/adminreport.do",null,"3");
		
		List<ScreportVO> list3 = redao.listSCre("1","1",SRcpage.getStartRow(), SRcpage.getEndRow());
		
		request.setAttribute("page3", SRcpage.getPage());
		request.setAttribute("list3", list3);
		request.setAttribute("count3", SRccount);
		
		return "/WEB-INF/views/adminster/ulimitlist.jsp";
	}

}
