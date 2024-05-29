package kr.used.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;
import kr.util.FileUtil;

public class UsedDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
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
		if(used.getU_image()!= null) {
			FileUtil.removeFile(request, used.getU_image());
		}
		dao.deleteUsed(u_num);
	
		request.setAttribute("notice_msg", "삭제되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/used/usedList.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
