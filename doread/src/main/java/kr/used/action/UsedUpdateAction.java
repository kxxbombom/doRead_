package kr.used.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;
import kr.util.FileUtil;

public class UsedUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Object user_num = session.getAttribute("user_num");
		if(user_num == null) {
			
			return "redirect:/member/loginForm.do";
		}
		Integer mem_num =(Integer)session.getAttribute("user_num");
		int u_num = Integer.parseInt(request.getParameter("u_num"));
		UsedDAO dao = UsedDAO.getInstance();
		UsedVO used = dao.detatilUsed(u_num); 
		if(used.getMem_num() != mem_num) {
			return "/WEB-INF/views/common/notice.jsp";
			
		}
		
		request.setCharacterEncoding("utf-8");
		String u_title = request.getParameter("u_title");
		String u_content= request.getParameter("u_content");
		int book_num = Integer.parseInt(request.getParameter("book_num"));
		int u_price = Integer.parseInt(request.getParameter("u_price"));
		//String u_image = request.getParameter("u_image");
		int u_condition = Integer.parseInt(request.getParameter("sel"));
		
		UsedVO usedvo = new UsedVO();
		usedvo.setU_ip(request.getRemoteAddr());
		usedvo.setU_num(u_num);
		usedvo.setMem_num(mem_num);
		usedvo.setBook_num(book_num);
		if(used.getU_image()!= null) {
			FileUtil.removeFile(request, used.getU_image());
		}
		usedvo.setU_image(FileUtil.createFile(request, "u_image"));
		usedvo.setU_condition(u_condition);
		usedvo.setU_title(u_title);
		usedvo.setU_content(u_content);
		usedvo.setU_price(u_price);
		usedvo.setU_state(Integer.parseInt(request.getParameter("u_state")));
		dao.updateUsed(usedvo);
		return "redirect:/used/usedDetail.do?u_num="+u_num;
	}

}
