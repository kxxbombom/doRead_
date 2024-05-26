package kr.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.qna.dao.QnaDAO;
import kr.qna.vo.QnaVO;
import kr.util.StringUtil;

public class QnaDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글 번호 반환
		int q_num = Integer.parseInt(request.getParameter("q_num"));
				
		QnaDAO dao = QnaDAO.getInstance();
				
		QnaVO qna = dao.getQna(q_num);
		//HTML허용하지 않음
		qna.setQ_title(StringUtil.useNoHTML(qna.getQ_title()));
		//HTML을 허용하지 않으면서 줄바꿈
		qna.setQ_content(StringUtil.useBrNoHTML(qna.getQ_content()));
		
		request.setAttribute("qna", qna);
		
		
		return "/WEB-INF/views/qna/qnaDetail.jsp";
	}

}
