package kr.order.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.order.dao.OrdersDAO;
import kr.order.vo.OrderVO;

public class UserModifyOrderInfoAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			map.put("result", "logout");
		}else {
			int order_num = Integer.parseInt(request.getParameter("order_num"));
		
			OrdersDAO dao = OrdersDAO.getInstance();
			
			OrderVO db_order = dao.getOrder(order_num);
			if(db_order.getMem_num() != user_num) {
				request.setAttribute("notice_msg", "잘못된 접근입니다.");
				request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
				return "/WEB-INF/views/common/alert_view.jsp";
			}
			
			OrderVO order = new OrderVO();
			order.setReceive_name(request.getParameter("receive_name"));
			order.setReceive_phone(request.getParameter("receive_phone"));
			order.setReceive_zipcode(request.getParameter("receive_zipcode"));
			order.setReceive_address1(request.getParameter("receive_address1"));
			order.setReceive_address2(request.getParameter("receive_address2"));
			order.setOrder_msg(request.getParameter("order_msg"));
			order.setEnter(Integer.parseInt(request.getParameter("enter")));
			order.setEnter_passwd(request.getParameter("enter_passwd"));
			
			dao.updateOrder(order, order_num);
			map.put("result", "success");
			map.put("order_num", order_num);
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
