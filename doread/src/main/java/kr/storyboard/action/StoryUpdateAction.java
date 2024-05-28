package kr.storyboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.StoryBoardVO;
import kr.util.FileUtil;

public class StoryUpdateAction implements Action{

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
		//전송된 데이터 반환
		int s_num = Integer.parseInt(request.getParameter("s_num"));
		StoryBoardDAO dao = StoryBoardDAO.getInstance();
		//수정 전 데이터
		StoryBoardVO db_story = dao.getStoryBoard(s_num);
		//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
		if(user_num != db_story.getMem_num()) {//로그인한 회원 번호와 작성자 회원번호 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		//로그인한 회원번호와 작성자 회원번호 일치
		StoryBoardVO sb = new StoryBoardVO();
		sb.setS_num(s_num);;
		sb.setS_title(request.getParameter("s_title"));
		sb.setS_content(request.getParameter("s_content"));
		sb.setS_ip(request.getRemoteAddr());
		sb.setS_image(FileUtil.createFile(request, "s_image"));
		sb.setBook_num(Integer.parseInt(request.getParameter("book_num")));
		
		dao.updateStory(sb);
		
		if(sb.getS_image()!=null && !"".equals(sb.getS_image())) {
			//새 파일로 교체할 떄 원래 파일 제거
			FileUtil.removeFile(request, db_story.getS_image());
		}
		
		
		return "redirect:/story/storyDetail.do?s_num="+s_num;
	}

}
