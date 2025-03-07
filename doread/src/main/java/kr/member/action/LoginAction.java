package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		if(member != null) {
			check = member.isCheckedPassword(passwd);
			
			request.setAttribute("auth", member.getMem_auth());
		}
		if(check) {
			HttpSession session = request.getSession();
			if(member.getMem_photo() != null) {
				session.setAttribute("user_photo", member.getMem_photo());
			}
			session.setAttribute("user_num", member.getMem_num());
			session.setAttribute("user_id", member.getMem_id());
			session.setAttribute("user_auth", member.getMem_auth());
			
			return "redirect:/main/main.do";
		}
		return "/WEB-INF/views/member/login.jsp";
	}

}
