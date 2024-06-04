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
import kr.order.dao.OrdersDAO;
import kr.order.vo.OrderVO;
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
		
		OrdersDAO dao = OrdersDAO.getInstance();
		int count = dao.getOrderCount(keyfield, keyword, user_num);
		
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 10, "mileagelist.do");
		
		List<OrderVO> list = null;
		if(count > 0) {
			list = dao.getListOrderByMem_num(page.getStartRow(), page.getEndRow(), keyfield, keyword, user_num);
		}
		
		CartDAO dao3 = CartDAO.getInstance();
		//회원번호별 총 구매액
		int all_total = dao3.getTotalByMem_num(user_num);
		int cart_count = 0;
		
		List<CartVO> list2 = null;
		if(all_total > 0){
			list2 = dao3.getListCart(user_num);
			cart_count = dao3.getCartCount(user_num);
		}
		
		//배송비 적용 총구매액
		int all_total_delivery = all_total;
		if(all_total < 15000) all_total_delivery += 2500;
		
		//적립금
		int point = (int) (all_total * 0.03);
		
		
		
		request.setAttribute("member", member);	
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("all_total", all_total);
		request.setAttribute("all_total_delivery", all_total_delivery);
		request.setAttribute("point", point);
		request.setAttribute("list2", list2);
		request.setAttribute("cart_count", cart_count);
		
		return "/WEB-INF/views/shopping/mileagelist.jsp";
	}

}
