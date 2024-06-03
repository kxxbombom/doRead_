package kr.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;

import kr.util.PagingUtil;

public class AdminListAction implements Action{

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
		String keyf = request.getParameter("keyf");
		String keyw = request.getParameter("keyw");
		if(pageNum == null) pageNum="1";
		OrderDAO order = OrderDAO.getInstance();
		int count = order.getAdminOrderCount(keyf,keyw);
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,10,10,request.getContextPath()+"/order/adminList.do");
		List<OrderVO> list = order.getAdminListOrderByMem_num(page.getStartRow(), page.getEndRow(), keyf, keyw);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("count", count);
		request.setAttribute("check", keyf);
		return "/WEB-INF/views/adminster/adminSelList.jsp";
	}

}
