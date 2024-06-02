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
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		String pageNum = request.getParameter("pageNum");
		String pageNum1 = request.getParameter("pageNum1");
		String pageNum2= request.getParameter("pageNum2");
		if(pageNum == null) pageNum = "1";
		if(pageNum1 == null) pageNum1 = "1";
		if(pageNum2 == null) pageNum2 = "1";
		String keyfield = request.getParameter("keyfield");
		String keyword= request.getParameter("keyword");
		
		//story
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		int count = dao.getStoryBoardCount(keyfield, keyword);
		PagingUtil page = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count, 30,10,"myPostList.do");
		List<StoryBoardVO> Storylist = null;
		if(count > 0) {
			Storylist = dao.getListMyStoryBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword, user_num);
		}
		request.setAttribute("page", page.getPage());
		request.setAttribute("Storylist", Storylist);
		
		//qna
		QnaDAO dao3 = QnaDAO.getInstance();
		int count3 = dao3.getQnaCount(keyfield, keyword);
		PagingUtil page3 = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum1),count3, 20,10,"myPostList.do",null,"1");
		List<QnaVO> qnalist = null;
		if(count3 > 0) {
			qnalist = dao3.getListQna(page3.getStartRow(), page3.getEndRow(), keyfield, keyword ,user_num);
		}
		request.setAttribute("page3", page3.getPage());
		request.setAttribute("qnalist", qnalist);
		UsedDAO udao = UsedDAO.getInstance();
		int countused = udao.myUsedCount(user_num);
		PagingUtil page4 = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum2),countused, 10,10,"myPostList.do",null,"2");

		List<UsedVO> ulist =udao.mylistUsed(page4.getStartRow(),page4.getEndRow(), user_num);
		request.setAttribute("page4", page4.getPage());
		request.setAttribute("list", ulist);
		// JSP 경로 반환
		return "/WEB-INF/views/post/myPostList.jsp";
	}
	
}
