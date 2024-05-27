package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		MemberVO member = new MemberVO();
		member.setMem_name(request.getParameter("name"));
		member.setMem_id(request.getParameter("id"));
		member.setMem_pw(request.getParameter("passwd"));
		member.setMem_email(request.getParameter("email"));
		member.setMem_phone(request.getParameter("phone"));
		member.setMem_zipcode(request.getParameter("zipcode"));
		member.setMem_address1(request.getParameter("address1"));
		member.setMem_address2(request.getParameter("address2"));
		String preference = request.getParameter("preference");
        if (preference != null) {
            try {
                member.setBook_category(Integer.parseInt(preference));
            } catch (NumberFormatException e) {
                member.setBook_category(null); // 파싱 실패 시 null로 설정
            }
        } else {
            member.setBook_category(null); // null 값 그대로 유지
        }
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(member);
		
		request.setAttribute("result_title", "회원 가입 완료");
		request.setAttribute("result_msg", "회원 가입이 완료되었습니다.");
		request.setAttribute("result_url", request.getContextPath() + "/main/main.do");
		
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}
