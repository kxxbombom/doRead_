package kr.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;

public class UpdateBookImageFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");

		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}

		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth!=9) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		int book_num = Integer.parseInt(request.getParameter("book_num"));
		BookDAO dao= BookDAO.getInstance();
		BookVO book= dao.getBookDetail(book_num);
		
		request.setAttribute("book", book);
		
		return "/WEB-INF/views/book/updateBookImageForm.jsp";
	}

}
