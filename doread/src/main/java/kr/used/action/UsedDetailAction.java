package kr.used.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;

public class UsedDetailAction implements Action	{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		UsedDAO dao = UsedDAO.getInstance();
		int u_num = Integer.parseInt(request.getParameter("u_num"));
		dao.hitUsed(u_num);
		UsedVO used = dao.detatilUsed(u_num);
	
		request.setAttribute("used", used);
		
		request.setAttribute("menu", "used");
		return "/WEB-INF/views/used/usedDetail.jsp";
	}

}
