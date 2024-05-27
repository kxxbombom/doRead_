package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		MemberVO member = new MemberVO();
		member.setMem_num(user_num);//회원번호
		member.setMem_name(request.getParameter("name"));
		member.setMem_phone(request.getParameter("phone"));
		member.setMem_email(request.getParameter("email"));
		member.setMem_zipcode(request.getParameter("zipcode"));
		member.setMem_address1(request.getParameter("address1"));
		member.setMem_address2(request.getParameter("address2"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);
		
		request.setAttribute("notice_msg", "회원정보 수정 완료");
		request.setAttribute("notice_url", 
		request.getContextPath()+"/member/mypage.do");
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}






