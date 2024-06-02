package kr.report.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.report.dao.ReportDAO;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.SCommentVO;
import kr.used.dao.UsedDAO;
import kr.used.vo.UBCommentVO;

public class ViewCommentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Object user_num = session.getAttribute("user_num");
		Integer auth = (Integer) session.getAttribute("user_auth");
		if(user_num == null || auth !=9 ) {
			
			map.put("result", "logout");		
		}else {

		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		if(name.equals("uc_num")) {
		int id = Integer.parseInt(request.getParameter("id"));
			UsedDAO dao = UsedDAO.getInstance();
			UBCommentVO ub = dao.getCommentUsed(id);
			map.put("result", "success");
			map.put("con", ub.getUc_content());
		}else if(name.equals("sc_num")) {
			int id = Integer.parseInt(request.getParameter("id"));
			StoryBoardDAO dao = StoryBoardDAO.getInstance();
			SCommentVO sb = dao.getCommentStory(id);
			map.put("result", "success");
			map.put("con", sb.getSc_content());
			
			
		}
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
