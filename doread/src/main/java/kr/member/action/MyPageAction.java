package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;


public class MyPageAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		//회원정보
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num);
		
		
		//추천 도서 표시
		BookDAO book = BookDAO.getInstance();
		int count = book.getRecommendBookCount(user_num);
		List<BookVO> recommend = book.getRecommendBook(user_num);
		
		request.setAttribute("member", member);	
		request.setAttribute("count", count);
		request.setAttribute("recommend", recommend);
		
		return "/WEB-INF/views/member/mypage.jsp";
	}

}
