package kr.storyboard.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.storyboard.dao.StoryBoardDAO;
import kr.storyboard.vo.SCommentVO;
import kr.util.PagingUtil;

public class StoryListCommentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 타입 지정
				request.setCharacterEncoding("utf-8");
				
				String pageNum = request.getParameter("pageNum");
				if(pageNum==null) {
					pageNum = "1";
				}
				String rowCount = request.getParameter("rowCount");
				if(rowCount ==null) {
					rowCount = "100";
				}
				
				int s_num = Integer.parseInt(request.getParameter("s_num"));
				
				StoryBoardDAO dao = StoryBoardDAO.getInstance();
				int count = dao.getCommentStoryCount(s_num);
				/*
				 * ajax 방식으로 목록을 표시하기 때문에 pagingUtil은 페이지 수 표시가 목적이 아니라 목록 데이터의 페이지 처리를 위해 rownum 번호를 구하는 것이 목적임
				 */
				PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,Integer.parseInt(rowCount),1,null);
				List<SCommentVO> list = null;
				if(count > 0) {
					list = dao.getListCommentStory(page.getStartRow(), page.getEndRow(), s_num);
					
				}else {
					list = Collections.emptyList();//빈배열 반환
				}
				
				HttpSession session = request.getSession();
				Integer user_num = (Integer)session.getAttribute("user_num");
				
				Map<String,Object> mapAjax = new HashMap<String,Object>();
				mapAjax.put("count",count);
				mapAjax.put("list", list);
				//로그인한 사람이 작성자인지 체크하기 위해서 로그인한 회원번호 전송
				mapAjax.put("user_num", user_num);
				
				//JSON 문자열로 변환
				ObjectMapper mapper = new ObjectMapper();
				String ajaxData = mapper.writeValueAsString(mapAjax);
				
				request.setAttribute("ajaxData", ajaxData);
				
				
				return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
