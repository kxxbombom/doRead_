package kr.used.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.used.dao.UsedDAO;
import kr.used.vo.UBCommentVO;

public class UBCommentDeleteAction implements Action{  

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");  
		//댓글 번호
		int uc_num = Integer.parseInt(request.getParameter("uc_num"));
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		UsedDAO dao = UsedDAO.getInstance();
		UBCommentVO db_ub = dao.getCommentUsed(uc_num);
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else if(user_num!=null && user_num == db_ub.getMem_num()) {//로그인한 회원번호와 작성자 회원번호 일치
			dao.deleteCommentUsed(uc_num);
			
			mapAjax.put("result", "success");
		}else {//로그인한 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
			
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
