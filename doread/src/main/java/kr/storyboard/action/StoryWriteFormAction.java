package kr.storyboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;

public class StoryWriteFormAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		List<BookVO> list = null;
		list = dao.getListBookByStory();
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/story/storyWriteForm.jsp";
	}
}