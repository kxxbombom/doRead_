package kr.used.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.used.vo.ReplyUsedVO;

public class ReplywriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Object user_num = session.getAttribute("user_num");
		if(user_num == null) {
			
			map.put("result", "logout");
		}else {
			Integer mem_num =(Integer)session.getAttribute("user_num");
			int u_num =Integer.parseInt(request.getParameter("u_num"));
			String uc_content = request.getParameter("uc_content");
			ReplyUsedVO reply = new ReplyUsedVO();
			reply.setMem_num(mem_num);
			reply.setU_num(u_num);
			reply.setUc_content(uc_content);
			
			UsedDAO dao = UsedDAO.getInstance();
			dao.replyUsedinsert(reply);
			
			map.put("result","success");
			
			
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
