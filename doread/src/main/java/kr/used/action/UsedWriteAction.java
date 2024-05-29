package kr.used.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;
import kr.util.FileUtil;

public class UsedWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Object user_num = session.getAttribute("user_num");
		if(user_num == null) {
			
			return "redirect:/member/loginForm.do";
		}
		Integer mem_num = (Integer) session.getAttribute("user_num");
		
		request.setCharacterEncoding("utf-8");
		String u_title = request.getParameter("u_title");
		String u_content= request.getParameter("u_content");
		int book_num = Integer.parseInt(request.getParameter("book_num"));
		int u_price = Integer.parseInt(request.getParameter("u_price"));
		//String u_image = request.getParameter("u_image");
		int u_condition = Integer.parseInt(request.getParameter("sel"));
		UsedDAO dao = UsedDAO.getInstance();
		UsedVO used = new UsedVO();
		used.setU_ip(request.getRemoteAddr());
		used.setMem_num(mem_num);
		used.setBook_num(book_num);
		used.setU_image(FileUtil.createFile(request, "u_image"));
		used.setU_condition(u_condition);
		used.setU_title(u_title);
		used.setU_content(u_content);
		used.setU_price(u_price);
		dao.insertUsed(used);
		
		return "redirect:/used/usedList.do";
	}

}
