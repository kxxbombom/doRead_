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

public class WriteFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환
			int book_num = Integer.parseInt(request.getParameter("book_num"));
			BookFavVO favVO = new BookFavVO();
			favVO.setMem_num(user_num);
			favVO.setBook_num(book_num);
			
			BookDAO dao = BookDAO.getInstance();
			//좋아요 등록 여부 체크
			BookFavVO db_fav = dao.selectFav(favVO);
			if(db_fav!=null) {//좋아요 등록 O
				//좋아요 삭제
				dao.deleteFav(db_fav);
				mapAjax.put("status", "noFav");
			}else {//좋아요 등록 X
				//좋아요 등록
				dao.insertFav(favVO);
				mapAjax.put("status", "yesFav");
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", dao.selectFavCount(book_num));
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
		
	}

}
