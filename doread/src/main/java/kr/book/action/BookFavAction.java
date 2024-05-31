package kr.book.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.book.dao.BookDAO;
import kr.controller.Action;
import kr.member.vo.BookFavVO;

public class BookFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int book_num = Integer.parseInt(request.getParameter("book_num"));
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		BookDAO dao = BookDAO.getInstance();
		if(user_num==null) {
			//mem_num이 비어있는 경우 == 로그인이 되지 않은 경우
			mapAjax.put("status", "noFav");
		}else {
			//로그인이 되어있는 경우
			BookFavVO bookFav = dao.selectFav(new BookFavVO(book_num,user_num));
			
			if(bookFav!=null) {
				//로그인한 회원이 해당 도서를 찜한 상태
				mapAjax.put("status", "yesFav");
			}else {
				//로그인한 회원이 해당 도서를 찜하지 않은 상태
				mapAjax.put("status","noFav");
			}
		}
				
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
