package kr.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.book.dao.BookDAO;
import kr.book.vo.BookVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateBookImageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		int book_num = Integer.parseInt(request.getParameter("book_num"));
		BookDAO dao = BookDAO.getInstance();
		BookVO db_book = dao.getBookDetail(book_num);
		
		BookVO book = new BookVO();
		
		book.setBook_num(book_num);
		book.setBook_img(FileUtil.createFile(request, "book_img"));
		
		dao.updateBookImage(book);
		
		if(book.getBook_img()!=null && 
				!"".equals(book.getBook_img())) {
			//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, db_book.getBook_img());
		}
		
		request.setAttribute("result_title", "도서 이미지 수정 완료");
		request.setAttribute("result_msg", "도서 이미지 수정이 완료되었습니다.");
		request.setAttribute("result_url", request.getContextPath()+"/book/updateBookForm.do?book_num="+book_num);

		return "/WEB-INF/views/common/result_view.jsp";
	}

}
