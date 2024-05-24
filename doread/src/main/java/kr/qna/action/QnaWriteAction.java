package kr.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.qna.dao.QnaDAO;
import kr.qna.vo.QnaVO;
import kr.util.FileUtil;

public class QnaWriteAction implements Action {

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
		QnaVO qna = new QnaVO();
		qna.setQ_title(request.getParameter("q_title"));
		qna.setQ_content(request.getParameter("q_content"));
		qna.setQ_ip(request.getRemoteAddr());
		qna.setQ_auth(Integer.parseInt(request.getParameter("q_auth")));
		qna.setQ_image(FileUtil.createFile(request, "q_iamge"));
		qna.setMem_num(user_num); //작성자 회원번호
		
		QnaDAO dao = QnaDAO.getInstance();
		dao.insertBoard(qna);
		
		request.setAttribute("notice_msg", "문의 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/qna/qnaList.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
