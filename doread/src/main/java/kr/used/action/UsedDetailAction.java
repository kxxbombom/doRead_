package kr.used.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.used.dao.UsedDAO;
import kr.used.vo.UsedVO;
import kr.util.StringUtil;

public class UsedDetailAction implements Action	{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int u_num = Integer.parseInt(request.getParameter("u_num"));
		
		UsedDAO dao = UsedDAO.getInstance();
		
		dao.hitUsed(u_num);
		UsedVO used = dao.detatilUsed(u_num);
		//HTML허용하지 않음
		used.setU_title(StringUtil.useNoHTML(used.getU_title()));
		//HTML을 허용하지 않으면서 줄바꿈
		used.setU_content(StringUtil.useBrNoHTML(used.getU_content()));
		
		request.setAttribute("used", used);
		
		return "/WEB-INF/views/used/usedDetail.jsp";
	}

}
