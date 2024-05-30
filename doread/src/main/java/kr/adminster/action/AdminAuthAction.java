package kr.adminster.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.report.dao.ReportDAO;

public class AdminAuthAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		Integer user_auth = 
				(Integer)session.getAttribute("user_auth");
		if(user_num == null || user_auth != 9) {//로그인이 되지 않은 경우
			map.put("result", "logout");
		}else {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			int num = Integer.parseInt(request.getParameter("num"));
			int auth = Integer.parseInt(request.getParameter("auth"));
			ReportDAO dao =ReportDAO.getInstance();
			if(name.equals("s_num")) {
				
				dao.updateAuthSre(auth, num);
				map.put("result", "success");
			}else if(name.equals("sc_num")) {
				
				dao.updateAuthSCre(auth, num);
				
				map.put("result", "success");
				
			}
			
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
