package kr.book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;

public class BookFavListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) { //로그인 안 된 경우
			return "redirect:/member/loginForm.jsp";
		}
		//로그인 된 경우
		BookDAO dao = BookDAO.getInstance();
		List<BookVO> bookList = dao.getListBookFav(1, 5, user_num);
		
		request.setAttribute("bookList", bookList);
		
		return "/WEB-INF/views/book/bookfav.jsp";
	}

}
