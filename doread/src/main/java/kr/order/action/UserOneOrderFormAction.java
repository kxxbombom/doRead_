package kr.order.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.order.dao.OrderDAO;

import kr.order.vo.OrderDetailVO;


public class UserOneOrderFormAction implements Action{

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
		request.setCharacterEncoding("utf-8");
		int book_quantity = Integer.parseInt(request.getParameter("book_quantity"));
		int book_price =Integer.parseInt(request.getParameter("book_price"));
		int book_category =Integer.parseInt(request.getParameter("book_category"));
		int all_total = book_quantity * book_price;
		int all_total_delivery = all_total;
		if(all_total < 15000 && book_category != 12) all_total_delivery += 2500;
		
		if(all_total <= 0) {
			request.setAttribute("notice_msg", "정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		//장바구니에 담겨있는 도서 정보
		int book_num=Integer.parseInt(request.getParameter("book_num"));
		BookDAO bookDAO = BookDAO.getInstance();
		BookVO book = bookDAO.getBookDetail(book_num);
			
		if(book.getStock() < book_quantity) {
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
		
		
		//기본배송지정보
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO member = memberDAO.getMember(user_num);
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		OrderDetailVO order = new OrderDetailVO();
		
		int point = orderDAO.getPoint(user_num);
		
		order.setBook_image(book.getBook_img());
		order.setBook_name(book.getBook_name());
		order.setBook_num(book.getBook_num());
		order.setOrder_quantity(book_quantity);
		order.setBook_price(book.getPrice());
		order.setBook_total(all_total_delivery);
		request.setAttribute("category", book_category);
		request.setAttribute("order", order);
		request.setAttribute("stock", book.getStock());
		request.setAttribute("all_total", all_total);
		request.setAttribute("all_total_delivery", all_total_delivery);
		request.setAttribute("member", member);
		request.setAttribute("point", point);
		
		return "/WEB-INF/views/order/user_OneorderForm.jsp";
	}
}