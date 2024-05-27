package kr.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.qna.dao.QnaDAO;
import kr.qna.vo.QnaVO;
import kr.util.FileUtil;

public class QnaDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int q_num = Integer.parseInt(request.getParameter("q_num"));
		QnaDAO dao = QnaDAO.getInstance();
		QnaVO db_qna= dao.getQna(q_num);
		//로그인한 회원번호와 작성자 회원 번호 일치 여부 체크
		if(user_num != db_qna.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		//로그인한 회원번호와 작성자 회원 번호 일치
		dao.deleteQna(q_num);
		//파일 삭제
		FileUtil.removeFile(request, db_qna.getQ_image()); 
		
		request.setAttribute("notice_msg", "글 삭제 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/qna/qnaList.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
