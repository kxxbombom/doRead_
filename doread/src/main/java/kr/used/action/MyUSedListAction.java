package kr.used.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;

public class MyUSedListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int mem_num = (Integer) request.getAttribute("mem_num");
		MemberDAO memdao =MemberDAO.getInstance();
		if(memdao.getMember(mem_num).getMem_num() != user_num) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		UsedDAO dao = UsedDAO.getInstance();
		List<UsedVO> list =dao.mylistUsed(10, 10, mem_num);
		request.setAttribute("list", list);
		return "/WEB-INF/views/post/myusedList.jsp";
	}

}
