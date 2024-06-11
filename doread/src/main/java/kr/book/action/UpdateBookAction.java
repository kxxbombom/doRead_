package kr.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateBookAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		int book_num = Integer.parseInt(request.getParameter("book_num"));
		BookDAO dao = BookDAO.getInstance();

		BookVO book = new BookVO();
		book.setBook_num(book_num);
		book.setBook_name(request.getParameter("book_name"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setPublish_date(request.getParameter("publish_date"));
		book.setPrice(Integer.parseInt(request.getParameter("price")));
		book.setStock(Integer.parseInt(request.getParameter("stock")));
		book.setBook_auth(Integer.parseInt(request.getParameter("book_auth")));
		book.setBook_category(Integer.parseInt(request.getParameter("book_category")));
		book.setBook_content(request.getParameter("book_content"));
		dao.UpdateBook(book);

		request.setAttribute("result_title", "도서 정보 수정 완료");
		request.setAttribute("result_msg", "도서 정보 수정이 완료되었습니다.");
		request.setAttribute("result_url", request.getContextPath()+"/book/adminBookList.do");

		return "/WEB-INF/views/common/result_view.jsp";
	}

}
