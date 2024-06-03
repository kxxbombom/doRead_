package kr.adminster.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.admin.dao.AdminDAO;
import kr.controller.Action;
import kr.member.vo.MemberVO;
import kr.util.PagingUtil;

public class AdminUserListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth = 
				(Integer)session.getAttribute("user_auth");
		if(user_auth != 9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum="1";
		AdminDAO dao = AdminDAO.getInstance();
		int count = dao.getMemberCountByAdmin(null, null);
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 10, 10, request.getContextPath()+"/adminster/userList.do");
		List<MemberVO> list = dao.getListMemberByAdmin(page.getStartRow(), page.getEndRow(), null, null);
		request.setAttribute("member", list);
		request.setAttribute("page", page.getPage());
		return "/WEB-INF/views/adminster/adminsterUserList.jsp";
	}

}
