package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderDetailVO;
import kr.util.PagingUtil;

public class MyEbooklistAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
	
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		OrderDAO dao = OrderDAO.getInstance();
		int count = dao.getEbookDetailCount(user_num);
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 5, 10, "myebookList.do");
		List<OrderDetailVO> orderdetail = dao.getEbookDetail(page.getStartRow(),page.getEndRow(),user_num);
		
		request.setAttribute("list", orderdetail);
		request.setAttribute("page", page.getPage());
		request.setAttribute("count", count);
		return "/WEB-INF/views/shopping/myEbookList.jsp";
	}

}
