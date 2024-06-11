package kr.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;

public class UserdeleteorderAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//주문번호 반환
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		
		OrderDAO dao = OrderDAO.getInstance();
		OrderVO db_order = dao.getBookOrdervo(order_num);
		if(db_order.getMem_num() != user_num) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		if(db_order.getOrder_status() != 4 || db_order.getOrder_status() != 5) {
			//배송준비중 이상
			request.setAttribute("notice_msg", "배송상태가 변경되어 주문을 삭제할 수 없습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/order/orderDetail.do?order_num=" + order_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		//주문 취소
		dao.deleteUserorder(order_num);
		
		request.setAttribute("notice_msg", "주문이 삭제되었습니다.");
		request.setAttribute("notice_url", request.getContextPath() + "/order/orderDetail.do?order_num=" + order_num);
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
