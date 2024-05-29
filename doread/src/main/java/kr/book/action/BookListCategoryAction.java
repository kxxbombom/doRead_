package kr.book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class BookListCategoryAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int book_category = Integer.parseInt(request.getParameter("book_category"));
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield=request.getParameter("keyfield");
		String keyword=request.getParameter("keyword");
		
		BookDAO dao = BookDAO.getInstance();
		int count = dao.getCategoryBookCount(keyfield, keyword, book_category);
		
		PagingUtil page = new PagingUtil(keyfield,keyword,Integer.parseInt(pageNum),count,20,10,"listCategory.do");
		
		List<BookVO> bookList = null;
		if(count>0) {
			bookList = dao.getCategoryListBook(page.getStartRow(), page.getEndRow(), keyfield, keyword, book_category);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("bookList", bookList);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/book/list_category.jsp";
	}

}
