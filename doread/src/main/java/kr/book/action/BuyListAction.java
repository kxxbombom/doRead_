package kr.book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.order.dao.OrdersDAO;
import kr.order.vo.OrderVO;
import kr.util.PagingUtil;

public class BuyListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		//회원정보
		MemberDAO dao2 = MemberDAO.getInstance();
		MemberVO member = dao2.getMember(user_num);
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		OrdersDAO dao = OrdersDAO.getInstance();
		int count = dao.getOrderCount(keyfield, keyword, user_num);
		
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 10, "buylist.do");
		
		List<OrderVO> list = null;
		if(count > 0) {
			list = dao.getListOrderByMem_num(page.getStartRow(), page.getEndRow(), keyfield, keyword, user_num);
		}
		
		request.setAttribute("member", member);	
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		
		return "/WEB-INF/views/shopping/buylist.jsp";
	}

}
