package kr.used.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;
import kr.util.PagingUtil;

public class UsedmainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		UsedDAO dao = UsedDAO.getInstance();
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum="1";
		String keys = request.getParameter("keys");
		String keyf= request.getParameter("keyf");
		String keyw = request.getParameter("keyw");
		if(keys == null) keys="0";
		
		int count = dao.countUsed(keys, keyf, keyw);
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,10,10,request.getContextPath()+"/used/usedList.do");
		
		List<UsedVO> list =dao.listUsed(page.getStartRow(), page.getEndRow(), keys, keyf, keyw); 
		request.setAttribute("list",list);
		request.setAttribute("count",count);
		request.setAttribute("page",page.getPage());
		request.setAttribute("menu", "used");
		return "/WEB-INF/views/used/usedmain.jsp";
	}

}
