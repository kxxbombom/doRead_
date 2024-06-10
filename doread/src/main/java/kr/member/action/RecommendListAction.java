package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class RecommendListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		BookDAO book_dao = BookDAO.getInstance();
		int count = book_dao.getRecommendBookCount(user_num);
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,20,10,"list.do");
		
		List<BookVO> bookList = null;
		if(count>0) {
			bookList = book_dao.getRecommendBook(user_num);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("bookList", bookList);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/member/recommendList.jsp";
	}

}
