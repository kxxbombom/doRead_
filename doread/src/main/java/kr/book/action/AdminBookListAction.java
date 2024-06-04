package kr.book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class AdminBookListAction implements Action{

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
		
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		BookDAO dao = BookDAO.getInstance();
		int count = dao.getBookCount(keyfield, keyword);
		
		PagingUtil page = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count,20,10,"adminBookList.do");
		
		List<BookVO> list = null;
		if(count>0) {
			list = dao.getListBook(page.getStartRow(),page.getEndRow(),keyfield,keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list",list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/book/admin_list.jsp";
	}

}
