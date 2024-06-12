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
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum="1";
		
		String keys = request.getParameter("sel");
		String keyf= request.getParameter("sel2");
		String keyw = request.getParameter("search");
		if(keys == null) keys="0";
		
		UsedDAO dao = UsedDAO.getInstance();
		int count = dao.countUsed(keys, keyf, keyw);
		
		PagingUtil page = new PagingUtil(keyf,keyw,Integer.parseInt(pageNum),count,10,10,"usedList.do");
		List<UsedVO> list =null;
		if(count > 0) {
			list = dao.listUsed(page.getStartRow(), page.getEndRow(), keys, keyf, keyw); 
		}
		
		request.setAttribute("list",list);
		request.setAttribute("count",count);
		request.setAttribute("page",page.getPage());
		request.setAttribute("menu", "used");
		request.setAttribute("check", keys);
		request.setAttribute("check2", keyf);
		return "/WEB-INF/views/used/usedmain.jsp";
	}

}
