package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
import kr.util.PagingUtil;

public class EbookReadAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		request.setCharacterEncoding("utf-8");
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		OrderDAO dao = OrderDAO.getInstance();
		OrderVO order = dao.getBookOrdervo(order_num);
		if(order.getMem_num() != user_num) {
			request.setAttribute("notice_msg", "책 읽을 권한이 없습니다. QNA 문의해주세요");
			return "/WEB-INF/views/common/close_view.jsp";
		}
		List<OrderDetailVO> detail = dao.getListOrder_Detail(order_num);
		BookDAO daobook = BookDAO.getInstance();
		BookVO book = daobook.getBookDetail(detail.get(0).getBook_num());
		
		request.setAttribute("book", book);

		return "/WEB-INF/views/shopping/myEbookRead.jsp";
	}

}
