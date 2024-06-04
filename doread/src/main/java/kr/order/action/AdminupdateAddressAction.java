package kr.order.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;

public class AdminupdateAddressAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String,String>();
				HttpSession session = request.getSession();
				Integer user_num = (Integer) session.getAttribute("user_num");
				if(user_num == null) {
					map.put("result","logout");
				}
				Integer user_auth = (Integer) session.getAttribute("user_auth");
				if(user_auth != 9) {
					map.put("result","logout");
					
				}
				int order_num = Integer.parseInt(request.getParameter("order_num"));
				
				OrderVO order = new OrderVO();
				
				
				order.setReceive_name(request.getParameter("receive_name"));
				order.setReceive_zipcode(request.getParameter("receive_zipcode"));
				order.setReceive_address1(request.getParameter("receive_address1"));
				order.setReceive_address2(request.getParameter("receive_address2"));
				order.setReceive_phone(request.getParameter("receive_phone"));
				order.setOrder_msg(request.getParameter("order_msg"));
				order.setOrder_num(order_num);
				
				OrderDAO dao = OrderDAO.getInstance();
				dao.updateOrder(order);
				map.put("result","success");
				ObjectMapper mapper = new ObjectMapper();
				String ajaxData = mapper.writeValueAsString(map);
				request.setAttribute("ajaxData", ajaxData);
				return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
