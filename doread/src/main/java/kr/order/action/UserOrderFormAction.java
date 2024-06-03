package kr.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.order.dao.OrderDAO;
import kr.order.dao.OrdersDAO;


public class UserOrderFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		//POST방식의 접근만 허용 (get 방식으로 주소 접근 불허	
		if(request.getMethod().toUpperCase().equals("GET")) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		
		}
		
		CartDAO dao = CartDAO.getInstance();
		int all_total = dao.getTotalByMem_num(user_num);
		int all_total_delivery = all_total;
		if(all_total < 15000) all_total_delivery += 2500;
		
		if(all_total <= 0) {
			request.setAttribute("notice_msg", "정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		//장바구니에 담겨있는 도서 정보
		List<CartVO> cartList = dao.getListCart(user_num);
		BookDAO bookDAO = BookDAO.getInstance();
		for(CartVO cart : cartList) {
			BookVO book = bookDAO.getBookDetail(cart.getBook_num());
			
			if(book.getStock() < cart.getC_quantity()) {
				//재고 수량 부족
				request.setAttribute("notice_msg", "[" + book.getBook_name() + "]재고수량 부족으로 주문 불가");
				request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
				return "/WEB-INF/views/common/alert_view.jsp";
			}
			
			if(book.getBook_auth() != 0) {
				request.setAttribute("notice_msg", "[" + book.getBook_name() + "]판매 중지로 주문 불가");
				request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
				return "/WEB-INF/views/common/alert_view.jsp";
			}
		}
		
		//기본배송지정보
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO member = memberDAO.getMember(user_num);
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		int point = orderDAO.getPoint(user_num);
				
		request.setAttribute("list", cartList);
		request.setAttribute("all_total", all_total);
		request.setAttribute("all_total_delivery", all_total_delivery);
		request.setAttribute("member", member);
		request.setAttribute("point", point);
		
		return "/WEB-INF/views/order/user_orderForm.jsp";
	}
}