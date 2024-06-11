package kr.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
import kr.order.vo.PointVO;

public class UserOneOrderAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		//POST방식의 접근만 허용
		if(request.getMethod().toUpperCase().equals("GET")) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		int book_num =Integer.parseInt(request.getParameter("book_num"));
		String book_name = request.getParameter("book_name");
		BookDAO bookDAO = BookDAO.getInstance();
		BookVO book = bookDAO.getBookDetail(book_num);
		int order_quantity = Integer.parseInt(request.getParameter("order_quantity"));
		int book_price =Integer.parseInt(request.getParameter("book_price"));
		int all_total = order_quantity * book_price;
		int all_total_delivery = all_total;
		if(all_total < 15000) all_total_delivery += 2500;
		if(all_total <= 0) {
			request.setAttribute("notice_msg", "정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		String usedpoint = request.getParameter("usedpoint");
		//개별상품 정보
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();
		
		
		if(book.getBook_auth() == 1) {
				request.setAttribute("notice_msg", "[" + book.getBook_name() + "] 판매 중지");
				request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
				return "/WEB-INF/views/common/alert_view.jsp";
			}
			if(book.getStock() < order_quantity) {
				request.setAttribute("notice_msg", "[" + book.getBook_name() + "] 재고수량 부족으로 주문 불가");
				request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
				return "/WEB-INF/views/common/alert_view.jsp";
			}
			
			//도서 개별 상세 정보
			OrderDetailVO orderDetail = new OrderDetailVO();
			orderDetail.setBook_num(book_num);
			orderDetail.setBook_name(book_name);
			orderDetail.setBook_price(book_price);
			orderDetail.setOrder_quantity(order_quantity);
			orderDetail.setBook_total(book_price*order_quantity);
		orderDetailList.add(orderDetail);
		//구매정보 담기
		OrderVO order = new OrderVO();
		
		order.setOrder_payment(Integer.parseInt(request.getParameter("payment")));//1신용카드2계좌이체3휴대폰결제
		order.setReceive_name(request.getParameter("receive_name"));
		order.setReceive_zipcode(request.getParameter("receive_zipcode"));
		order.setReceive_address1(request.getParameter("receive_address1"));
		order.setReceive_address2(request.getParameter("receive_address2"));
		order.setReceive_phone(request.getParameter("receive_phone"));
		order.setOrder_msg(request.getParameter("order_msg"));
		order.setEnter(Integer.parseInt(request.getParameter("enter")));
		order.setEnter_passwd(request.getParameter("enter_passwd"));
		order.setMem_num(user_num);
		order.setAll_total(all_total);
		if(book.getBook_category() ==12)
		order.setOrder_status(4);
		
		if(usedpoint != null ) {
		order.setOrder_usepoint(Integer.parseInt(usedpoint));
		
		order.setOrder_total(all_total_delivery - Integer.parseInt(usedpoint));}
		else {
		order.setOrder_total(all_total_delivery);
		}
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.insertOrder(order, orderDetailList,1);
		
	
		request.setAttribute("result_title", "도서 주문 완료");
		request.setAttribute("result_msg", "주문이 완료되었습니다.");
		request.setAttribute("result_url", request.getContextPath() + "/main/main.do");
		
		return "/WEB-INF/views/order/user_order.jsp";
	}

}
