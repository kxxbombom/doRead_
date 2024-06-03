package kr.qna.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.qna.dao.QnaDAO;
import kr.qna.vo.QnaVO;
import kr.util.PagingUtil;
  
public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth= (Integer)session.getAttribute("user_auth");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword= request.getParameter("keyword");
		
		QnaDAO dao = QnaDAO.getInstance();
		int count = dao.getQnaCount(keyfield, keyword);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword,Integer.parseInt(pageNum),count, 10,10,"qnaList.do");
		List<QnaVO> list = null;
		if(count > 0) {
			list = dao.getListQna(page.getStartRow(), page.getEndRow(), keyfield, keyword,user_num);
		}
		List<QnaVO> list2 = null;
		if(count > 0) {
			list2 = dao.getListQnaForAdmin(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		request.setAttribute("user_num", user_num);
		request.setAttribute("user_auth", user_auth);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("page", page.getPage());
		
		// JSP 경로 반환
		return "/WEB-INF/views/qna/list.jsp";
	}

}
