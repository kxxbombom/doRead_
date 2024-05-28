package kr.storyboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.FileUtil;

public class StoryWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)생성한후 전송된 데이터를 저장
		StoryBoardVO sb = new StoryBoardVO();
		sb.setS_title(request.getParameter("s_title"));
		sb.setS_content(request.getParameter("s_content"));
		sb.setS_ip(request.getRemoteAddr());
		sb.setS_image(FileUtil.createFile(request, "s_image"));
		sb.setMem_num(user_num); //작성자 회원번호
		
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		dao.insertStoryBoard(sb);
		
		request.setAttribute("notice_msg", "스토리 작성 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/story/storyBoardList.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
