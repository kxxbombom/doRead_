package kr.storyboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.report.dao.ReportDAO;
import kr.report.vo.SreportVO;

public class ReportMainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Object user_num = session.getAttribute("user_num");
		if(user_num == null) {
			
			map.put("result", "logout");		
		}else {
		Integer mem_num = (Integer) session.getAttribute("user_num");
		request.setCharacterEncoding("utf-8");
		String cate = request.getParameter("cate");
		
		if(cate.equals("s_num")) {
			int s_num = Integer.parseInt(request.getParameter("num"));
			int s_category = Integer.parseInt(request.getParameter("category"));
			String content = request.getParameter("content");
			SreportVO sr = new SreportVO();
			sr.setMem_num(mem_num);
			sr.setS_num(s_num);
			sr.setSr_content(content);
			sr.setSr_category(s_category);
			
			
			ReportDAO dao = ReportDAO.getInstance();
			dao.insertStroyre(sr);
			map.put("result", "success");
		}
		
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
