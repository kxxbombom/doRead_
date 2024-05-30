package kr.report.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.report.dao.ReportDAO;
import kr.report.vo.ScreportVO;
import kr.report.vo.SreportVO;
import kr.report.vo.UcReportVO;
import kr.report.vo.UsedReportVO;

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
		ReportDAO dao = ReportDAO.getInstance();
	
		if(cate.equals("s_num")) {
			int s_num = Integer.parseInt(request.getParameter("num"));
			int s_category = Integer.parseInt(request.getParameter("category"));
			if(dao.checkedSre(s_num, mem_num) != null) {
				map.put("result", "duple");
				
			}else {
			
			String content = request.getParameter("content");
			SreportVO sr = new SreportVO();
			sr.setMem_num(mem_num);
			sr.setS_num(s_num);
			sr.setSr_content(content);
			sr.setSr_category(s_category);
			
			
			
			dao.insertStroyre(sr);
			map.put("result", "success");
			}
		}else if(cate.equals("sc_num")) {
			int s_num = Integer.parseInt(request.getParameter("num"));
			int s_category = Integer.parseInt(request.getParameter("category"));
			if(dao.checkedSCre(s_num, mem_num) != null) {
				map.put("result", "duple");
				
			}else {
			String content = request.getParameter("content");
			ScreportVO sr = new ScreportVO();
			sr.setMem_num(mem_num);
			sr.setSc_num(s_num);
			sr.setSrc_content(content);
			sr.setSrc_category(s_category);
			
			
			
			dao.insertSCtroyre(sr);
			map.put("result", "success");
			
			}
		}else if(cate.equals("u_num")) {
			int s_num = Integer.parseInt(request.getParameter("num"));
			int s_category = Integer.parseInt(request.getParameter("category"));
			if(dao.checkedU(s_num, mem_num)!=null ) {
				map.put("result", "duple");
				
			}else {
			
			String content = request.getParameter("content");
			UsedReportVO sr = new UsedReportVO();
			sr.setMem_num(mem_num);
			sr.setU_num(s_num);
			sr.setUr_content(content);
			sr.setUr_category(s_category);
			
			
			
			dao.insertUR(sr);
			map.put("result", "success");
			}
		}else if(cate.equals("uc_num")) {
			int s_num = Integer.parseInt(request.getParameter("num"));
			int s_category = Integer.parseInt(request.getParameter("category"));
			if(dao.checkedUC(s_num, mem_num)!=null ) {
				map.put("result", "duple");
				
			}else {
			
			String content = request.getParameter("content");
			UcReportVO sr = new UcReportVO();
			sr.setMem_num(mem_num);
			sr.setUc_num(s_num);
			sr.setUrc_content(content);
			sr.setUrc_category(s_category);
			
			
			
			dao.insertURC(sr);
			map.put("result", "success");
			}
		}
		
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
