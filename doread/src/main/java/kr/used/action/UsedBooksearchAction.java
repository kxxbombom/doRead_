package kr.used.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.util.PagingUtil;

public class UsedBooksearchAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Object user_num = session.getAttribute("user_num");
		if(user_num == null) {
			
			return "redirect:/member/loginForm.do";
		}
		
		String keyfeild = request.getParameter("book_name");
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum="1";
		UsedDAO dao = UsedDAO.getInstance();
		int count = dao.getCategoryListBookCount(keyfeild);
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,10,10,null);
		List<BookVO> list = dao.getCategoryListBook(keyfeild,page.getStartRow(),page.getEndRow());
		if(list == null) {
			map.put("result", "none");
			
		}else {
			map.put("result", "success");
			map.put("list", list);
			map.put("count", count);
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
