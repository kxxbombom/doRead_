package kr.book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;

public class BookListCategoryAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int book_category = Integer.parseInt(request.getParameter("book_category"));
		BookDAO dao = BookDAO.getInstance();
		List<BookVO> bookList = dao.getCategoryListBook(1, 20, null, null, book_category);
		
		
		request.setAttribute("bookList", bookList);
		
		return "/WEB-INF/views/book/list_category.jsp";
	}

}
