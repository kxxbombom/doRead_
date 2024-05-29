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

public class UBCommentWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인이 된 경우
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			//자바빈(VO)를 생성해서 전송된 데이터 저장
			UBCommentVO sc= new UBCommentVO();
			sc.setMem_num(user_num);//작성자 회원번호
			sc.setUc_content(request.getParameter("uc_content"));
			sc.setU_num(Integer.parseInt(request.getParameter("u_num")));//댓글의 부모 글 번호
			
			UsedDAO dao = UsedDAO.getInstance();
			dao.insertCommentUsed(sc);
			
			mapAjax.put("result", "success");
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
