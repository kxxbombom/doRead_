package kr.storyboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.StringUtil;

public class StoryDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글 번호 반환
		int s_num = Integer.parseInt(request.getParameter("s_num"));
						
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
						
		StoryBoardVO sb = dao.getStoryBoard(s_num);
		//HTML허용하지 않음
		sb.setS_title(StringUtil.useNoHTML(sb.getS_title()));
		//HTML을 허용하지 않으면서 줄바꿈
		sb.setS_content(StringUtil.useBrNoHTML(sb.getS_content()));
				
		request.setAttribute("sb", sb);
				
				
		return "/WEB-INF/views/story/storyDetail.jsp";
	}

}
