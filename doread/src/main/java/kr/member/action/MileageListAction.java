package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.order.dao.OrderDAO;
import kr.order.dao.OrdersDAO;
import kr.order.vo.OrderVO;
import kr.order.vo.PointVO;
import kr.util.PagingUtil;

public class MileageListAction implements Action{

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
		
		
		
		OrderDAO dao = OrderDAO.getInstance();

		int count = dao.getPagePoint(user_num);
		
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 10, "mileagelist.do");
		
		List<PointVO> list = null;
		if(count > 0) {
			list = dao.getListUserPoint(page.getStartRow(), page.getEndRow(), user_num);
		}
		
		//총적립금
		int allpoint = dao.getPoint(user_num);
		
		request.setAttribute("member", member);	
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());

		request.setAttribute("allpoint", allpoint);
		
		return "/WEB-INF/views/shopping/mileagelist.jsp";
	}

}
