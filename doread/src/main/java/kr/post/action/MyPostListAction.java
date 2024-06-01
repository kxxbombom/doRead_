package kr.post.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.qna.dao.QnaDAO;
import kr.qna.vo.QnaVO;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;
import kr.util.PagingUtil;
  
public class MyPostListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword= request.getParameter("keyword");
		Integer user_num = (Integer)session.getAttribute("user_num");
		//story
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		int count = dao.getStoryBoardCount(keyfield, keyword);
		PagingUtil page = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count, 20,10,"storyBoardList.do");
		List<StoryBoardVO> Storylist = null;
		
		if(count > 0) {
			Storylist = dao.getListMyStoryBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword, user_num);
		}
		
		request.setAttribute("Storylist", Storylist);
		
		//qna
		QnaDAO dao3 = QnaDAO.getInstance();
		int count3 = dao3.getQnaCount(keyfield, keyword);
		PagingUtil page3 = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count, 20,10,"qnaList.do");
		List<QnaVO> qnalist = null;
		if(count3 > 0) {
			qnalist = dao3.getListQna(page3.getStartRow(), page3.getEndRow(), keyfield, keyword ,user_num);
		}
		
		request.setAttribute("qnalist", qnalist);
		UsedDAO udao = UsedDAO.getInstance();
		List<UsedVO> ulist =udao.mylistUsed(0, 10, user_num);
		request.setAttribute("list", ulist);
		// JSP 경로 반환
		return "/WEB-INF/views/post/myPostList.jsp";
	}
	
}
