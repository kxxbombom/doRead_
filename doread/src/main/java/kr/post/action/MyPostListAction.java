package kr.post.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.vo.OrderVO;
import kr.qna.dao.QnaDAO;
import kr.qna.vo.QnaVO;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.PagingUtil;
  
public class MyPostListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword= request.getParameter("keyword");
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		//story
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		int count = dao.getStoryBoardCount(keyfield, keyword);
		PagingUtil page = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count, 20,10,"storyBoardList.do");
		List<StoryBoardVO> storylist = null;
		
		if(count > 0) {
			storylist = dao.getListMyStoryBoard(1, 5, null, null, user_num);
		}
		
		request.setAttribute("storylist", storylist);
		
		//qna
		QnaDAO dao2 = QnaDAO.getInstance();
		List<QnaVO> qnalist = null;
		if(count > 0) {
			qnalist = dao2.getListQna(page.getStartRow(), page.getEndRow(), keyfield, keyword ,user_num);
		}
		
		request.setAttribute("qnalist", qnalist);
		
		// JSP 경로 반환
		return "/WEB-INF/views/post/myPostList.jsp";
	}
	
}
