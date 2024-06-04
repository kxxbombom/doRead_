package kr.member.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.FileUtil;


public class MyPageAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Map<String,String> mapAjax = 
	            new HashMap<String,String>();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			//파일 업로드 처리
			String photo = 
					FileUtil.createFile(request, "photo");

			MemberDAO dao = MemberDAO.getInstance();
			//프로필 사진 수정
			dao.updateMyPhoto(photo, user_num);

			//이전 파일 삭제 처리
			String user_photo = 
				 (String)session.getAttribute("user_photo");
			FileUtil.removeFile(request, user_photo);

			//현재 파일로 세션 정보 갱신
			session.setAttribute("user_photo", photo);

			mapAjax.put("result", "success");
		}
		
		//로그인이 된 경우
		//회원정보
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num);
		
		
		//추천 도서 표시
		BookDAO book = BookDAO.getInstance();
		int count = book.getRecommendBookCount(user_num);
		List<BookVO> recommend = book.getRecommendBook(user_num);
		
		request.setAttribute("member", member);	
		request.setAttribute("count", count);
		request.setAttribute("recommend", recommend);
		
		return "/WEB-INF/views/member/mypage.jsp";
	}

}
