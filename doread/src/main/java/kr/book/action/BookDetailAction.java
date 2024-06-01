package kr.book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.StringUtil;

public class BookDetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int book_num = Integer.parseInt(request.getParameter("book_num"));
		BookDAO dao = BookDAO.getInstance();
		
		BookVO book = dao.getBookDetail(book_num);
		book.setBook_name(StringUtil.useNoHTML(book.getBook_name()));
		
		int s_count = dao.getBookStoryCount(book_num);
		List<StoryBoardVO> story = dao.getBookStory(book_num);
		
		request.setAttribute("s_count", s_count);
		request.setAttribute("book", book);
		request.setAttribute("story", story);
		
		return "/WEB-INF/views/book/detail.jsp";
	}

}
