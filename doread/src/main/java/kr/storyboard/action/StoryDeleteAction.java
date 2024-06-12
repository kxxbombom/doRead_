package kr.storyboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.FileUtil;

public class StoryDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth= (Integer)session.getAttribute("user_auth");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		StoryBoardVO db_story= dao.getStoryBoard(s_num);
		//로그인한 회원번호와 작성자 회원 번호 일치 여부 체크
		if(user_num != db_story.getMem_num()) {
			if(user_auth != 9) return "/WEB-INF/views/common/notice.jsp";
		}
		//로그인한 회원번호와 작성자 회원 번호 일치
		dao.deleteStory(s_num);
		//파일 삭제
		FileUtil.removeFile(request, db_story.getS_image()); 
		
		request.setAttribute("notice_msg", "글 삭제 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/story/storyBoardList.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
