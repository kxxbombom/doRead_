package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class UpdateCategoryAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		MemberVO member = new MemberVO();
		member.setMem_num(user_num);
		String[] preference = request.getParameterValues("preference");
		if (preference == null) {
		    member.setBook_category(null);
		    member.setBook_category2(null);
		    member.setBook_category3(null);
		} else {
		    switch (preference.length) {
		        case 3:
		            try {
		                member.setBook_category(Integer.parseInt(preference[0]));
		                member.setBook_category2(Integer.parseInt(preference[1]));
		                member.setBook_category3(Integer.parseInt(preference[2]));
		            } catch (NumberFormatException e) {
		                member.setBook_category(null);
		                member.setBook_category2(null);
		                member.setBook_category3(null);
		            }
		            break;
		        case 2:
		            try {
		                member.setBook_category(Integer.parseInt(preference[0]));
		                member.setBook_category2(Integer.parseInt(preference[1]));
		                member.setBook_category3(null);
		            } catch (NumberFormatException e) {
		                member.setBook_category(null);
		                member.setBook_category2(null);
		                member.setBook_category3(null);
		            }
		            break;
		        case 1:
		            try {
		                member.setBook_category(Integer.parseInt(preference[0]));
		                member.setBook_category2(null);
		                member.setBook_category3(null);
		            } catch (NumberFormatException e) {
		                member.setBook_category(null);
		                member.setBook_category2(null);
		                member.setBook_category3(null);
		            }
		            break;
		        default:
		            member.setBook_category(null);
		            member.setBook_category2(null);
		            member.setBook_category3(null);
		            break;
		    }
		}
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateCategory(member);
		
		request.setAttribute("result_title", "카테고리 변경 완료");
		request.setAttribute("result_msg", "관심도서 변경이 완료되었습니다");
		request.setAttribute("result_url",request.getContextPath()+"/member/mypage.do");
		
		return "/WEB-INF/views/common/result_view.jsp";
	}

}
