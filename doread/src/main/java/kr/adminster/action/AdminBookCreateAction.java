package kr.adminster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AdminBookCreateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		BookVO book = new BookVO();
		book.setBook_name(request.getParameter("book_name"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setPublish_date(request.getParameter("publish_date"));
		book.setPrice(Integer.parseInt(request.getParameter("price")));
		book.setStock(Integer.parseInt(request.getParameter("stock")));
		book.setBook_category(Integer.parseInt(request.getParameter("book_category")));
		book.setBook_img(FileUtil.createFile(request,"book_img"));
		book.setBook_auth(Integer.parseInt(request.getParameter("book_auth")));
		BookDAO dao = BookDAO.getInstance();
		dao.InsertBook(book);
		
		request.setAttribute("result_title", "책 등록 완료");
		request.setAttribute("result_msg", "책 등록 완료");
		request.setAttribute("result_url", request.getContextPath()+"/adminster/adminPage.do");
		
		return "/WEB-INF/views/common/result_view.jsp";
	}

}
