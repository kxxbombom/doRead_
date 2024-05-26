package kr.adminster.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.admin.dao.AdminDAO;
import kr.controller.Action;

public class AdminAuthUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			map.put("result", "logout");
		}
		
		Integer user_auth = 
				(Integer)session.getAttribute("user_auth");
		if(user_auth != 9) {//관리자로 로그인하지 않은 경우
			map.put("result", "notadmin");
		}
		int auth = Integer.parseInt(request.getParameter("auth"));
		int mem_number =Integer.parseInt(request.getParameter("mem_num"));
		AdminDAO dao = AdminDAO.getInstance();
		dao.updateAuth(auth, mem_number);
		map.put("result", "success");
		map.put("auth", auth);
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
