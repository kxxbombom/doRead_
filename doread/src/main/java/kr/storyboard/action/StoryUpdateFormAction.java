package kr.storyboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.StringUtil;

public class StoryUpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		StoryBoardVO sb= dao.getStoryBoard(s_num);
		if(user_num!=sb.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//큰 따옴표 처리
		//수정폼의 input 태그에서 큰 따옴표 오류 보정
		sb.setS_title(StringUtil.parseQuot(sb.getS_title()));
		List<BookVO> list = null;
		list = dao.getListBookByStory();
		//로그인이 되어있고 로그인한 회원번호와 작성자 회원 번호 일치
		request.setAttribute("list", list);
		request.setAttribute("sb", sb);
		
		return "/WEB-INF/views/story/storyUpdateForm.jsp";
	}

}
