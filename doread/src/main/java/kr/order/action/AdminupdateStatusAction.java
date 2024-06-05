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

public class AdminupdateStatusAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		Integer user_auth = (Integer) session.getAttribute("user_auth");
		if(user_num == null || user_auth != 9) {
			map.put("result","logout");
			
		}else {
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		int status = Integer.parseInt(request.getParameter("status"));
		OrderDAO dao = OrderDAO.getInstance();
		OrderVO order = dao.getBookOrdervo(order_num);
		if(order.getOrder_status() == 5) {
			map.put("result", "cancle");
			
		}else {
		dao.updatestatus(order_num, status);
		map.put("result","success");
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		request.setAttribute("ajaxData", ajaxData);
		}
		}
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
