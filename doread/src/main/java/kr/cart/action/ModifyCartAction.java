package kr.cart.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;

public class ModifyCartAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		int total = 0;
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			mapAjax.put("result","logout");
		}else {
			request.setCharacterEncoding("utf-8");
			
			int book_num = Integer.parseInt(request.getParameter("book_num"));
			int c_quantity = Integer.parseInt(request.getParameter("c_quantity"));
			
			
			BookDAO bookDAO = BookDAO.getInstance();
			BookVO book = bookDAO.getBookDetail(book_num);
			if(book.getStock() < c_quantity) {
				mapAjax.put("result", "overQuantity");
			}else {
				CartVO cart = new CartVO();
				cart.setC_num(Integer.parseInt(request.getParameter("cart_num")));
				cart.setC_quantity(c_quantity);
				
				CartDAO cartDAO = CartDAO.getInstance();
				cartDAO.updateCart(cart);
				mapAjax.put("result", "success");
				
				total = cartDAO.getTotalByMem_num(user_num);
				if(total < 15000) total = total+3000;
				mapAjax.put("total", total);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
