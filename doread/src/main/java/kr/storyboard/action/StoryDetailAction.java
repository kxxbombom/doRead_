package kr.storyboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.StringUtil;

public class StoryDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//글 번호 반환
		int s_num = Integer.parseInt(request.getParameter("s_num"));
						
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		//조회수 증가
		dao.updateReadcount(s_num);
		StoryBoardVO sb = dao.getStoryBoard(s_num);
		//HTML허용하지 않음
		sb.setS_title(StringUtil.useNoHTML(sb.getS_title()));
		//HTML을 허용하지 않으면서 줄바꿈
		sb.setS_content(StringUtil.useBrNoHTML(sb.getS_content()));
				
		request.setAttribute("sb", sb);
		request.setAttribute("user_auth",user_auth);
				
				
		return "/WEB-INF/views/story/storyDetail.jsp";
	}

}
