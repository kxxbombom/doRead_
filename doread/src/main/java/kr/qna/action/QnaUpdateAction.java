package kr.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.qna.dao.QnaDAO;
import kr.qna.vo.QnaVO;
import kr.util.FileUtil;

public class QnaUpdateAction implements Action{

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
		QnaVO db_qna = dao.getQna(q_num);
		//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
		if(user_num != db_qna.getMem_num()) {//로그인한 회원 번호와 작성자 회원번호 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		//로그인한 회원번호와 작성자 회원번호 일치
		QnaVO qna = new QnaVO();
		qna.setQ_num(q_num);;
		qna.setQ_title(request.getParameter("q_title"));
		qna.setQ_content(request.getParameter("q_content"));
		qna.setQ_ip(request.getRemoteAddr());
		qna.setQ_image(FileUtil.createFile(request, "q_image"));
		
		dao.updateQna(qna);
		
		if(qna.getQ_image()!=null && !"".equals(qna.getQ_image())) {
			//새 파일로 교체할 떄 원래 파일 제거
			FileUtil.removeFile(request, db_qna.getQ_image());
		}
		
		
		return "redirect:/qna/qnaDetail.do?q_num="+q_num;
	}

}
