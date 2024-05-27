package kr.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.qna.dao.QnaDAO;
import kr.qna.vo.QnaVO;

public class QnaUpdateAnswerAction implements Action{

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
		int q_num = Integer.parseInt(request.getParameter("q_num"));
		QnaDAO dao = QnaDAO.getInstance();
		//수정 전 데이터
		QnaVO qna = new QnaVO();
		qna.setQ_num(q_num);;
		qna.setQ_answer(request.getParameter("q_answer"));
		
		dao.updateQnaAnswer(qna);
		
		
		
		return "redirect:/qna/qnaDetail.do?q_num="+q_num;
	}

}
