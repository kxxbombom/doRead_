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

public class InsertCartAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			mapAjax.put("result", "logout");
		}else {
			request.setCharacterEncoding("utf-8");
			
			CartVO cart = new CartVO();
			cart.setBook_num(Integer.parseInt(request.getParameter("book_num")));
			cart.setC_quantity(Integer.parseInt(request.getParameter("c_quantity")));
			cart.setMem_num(user_num);
			
			// 동일 상품 유무 확인. 동일 상품일 경우 합치기 & 재고수량 확인
			CartDAO dao = CartDAO.getInstance();
			CartVO db_cart = dao.getCart(cart);
			if(db_cart == null) {//동일 상품이 없을 경우
				dao.insertCart(cart);
				mapAjax.put("result", "success");
			}else {//동일 상품이 있을 경우
				//재고확인
				BookDAO bookDAO = BookDAO.getInstance();
				BookVO book = bookDAO.getBookDetail(db_cart.getBook_num());
				
				//구매수량 합산
				int c_quantity = db_cart.getC_quantity() + cart.getC_quantity();
				if(book.getStock() < c_quantity) {
					//상품재고 수량보다 장바구니에 담은 구매수량이 더 많음
					mapAjax.put("result", "overquantity");
				}else {
					cart.setC_quantity(c_quantity);
					dao.updateCartByBook_num(cart);
					mapAjax.put("result", "success");
				}
				
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
