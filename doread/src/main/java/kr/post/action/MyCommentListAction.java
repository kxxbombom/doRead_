package kr.post.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.SCommentVO;
import kr.used.dao.UsedDAO;
import kr.used.vo.UBCommentVO;
import kr.util.PagingUtil;

public class MyCommentListAction implements Action{

	@Override  
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword= request.getParameter("keyword");
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우
		//회원정보
		MemberDAO dao3 = MemberDAO.getInstance();
		MemberVO member = dao3.getMember(user_num);
		
		request.setAttribute("member", member);	
		//story
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		int count = dao.getCommentStoryCountByUser_num(user_num);
		PagingUtil page = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count, 5,10,"storyBoardList.do");
		List<SCommentVO> SCommentList = null;
		if(count > 0) {
			SCommentList = dao.getListCommentStoryByUser_num(page.getStartRow(), page.getEndRow(), user_num);
		}
		
		
		request.setAttribute("SCommentList", SCommentList);
		
		//used
		UsedDAO dao2 = UsedDAO.getInstance();
		int count2 = dao2.getCommentUsedCountByUser_num(user_num);
		PagingUtil page2= new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count, 5,10,"usedList.do");
		List<UBCommentVO> UBCList = null;
		if(count2 > 0) {
			UBCList = dao2.getListCommentUsedByUser_num(page2.getStartRow(), page2.getEndRow(), user_num);
		}
		
		
		request.setAttribute("UBCList", UBCList);
		
		
		
		// JSP 경로 반환
		return "/WEB-INF/views/post/myCommentList.jsp";
	}

}
