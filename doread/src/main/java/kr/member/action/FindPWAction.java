package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class FindPWAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		String passwd = request.getParameter("passwd");
		Integer num;
		String numParam = request.getParameter("num");
		if(numParam != null && !numParam.isEmpty()) {
			num = Integer.parseInt(numParam);
		}else {
			num = null;
		}
		if(phone == null || passwd == null || num == null) {
			request.setAttribute("notice_msg", "잘못된 접근입니다");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO db_member = dao.checkMember(id);
		String db_phone = db_member.getMem_phone();
		if(db_phone.equals(phone)) {
			dao.resetPW(passwd, num);
			mapAjax.put("result", "success");
		}else {
			mapAjax.put("result", "wrongInfo");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/member/findPW.jsp";
	}

}
