package kr.book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;

public class BookListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO dao = BookDAO.getInstance();
		List<BookVO> bookList = dao.getListBook(1, 20, null, null);
		
		request.setAttribute("bookList", bookList);
		
		return "/WEB-INF/views/book/list.jsp";
	}

}
