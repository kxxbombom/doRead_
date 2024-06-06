package kr.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cart.vo.CartVO;
import kr.book.vo.BookVO;
import kr.util.DBUtil;

public class CartDAO {
private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	private CartDAO() {}
	
	//장바구니 등록
	public void insertCart(CartVO cart)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "INSERT INTO cart (c_num, c_quantity, book_num, mem_num) VALUES (cart_seq.nextval,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cart.getC_quantity());
			pstmt.setInt(2, cart.getBook_num());
			pstmt.setInt(3, cart.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//회원번호별 총구매액
	public int getTotalByMem_num(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int total = 0;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT SUM(sub_total) FROM (SELECT mem_num, c_quantity * price AS sub_total FROM cart JOIN book USING(book_num) WHERE mem_num=?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1); // 집합함수 => 하나의 행
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return total;
	}
	
	//장바구니 상품전체개수(주문하기 표시)
	public int getCartTotalCount(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT SUM(c_quantity) FROM cart WHERE mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}	
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	
	//장바구니 상품개수
	public int getCartCount(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT COUNT(*) FROM cart WHERE mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}	
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//장바구니 목록
	public List<CartVO> getListCart(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CartVO> list = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM cart c JOIN book b USING(book_num) WHERE mem_num=? ORDER BY c.c_rdate DESC";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			list = new ArrayList<CartVO>();
			while(rs.next()) {
				CartVO cart = new CartVO();
				cart.setC_num(rs.getInt("c_num"));
				cart.setBook_num(rs.getInt("book_num"));
				cart.setC_quantity(rs.getInt("c_quantity"));
				cart.setMem_num(rs.getInt("mem_num"));
				
				//상품 정보를 담기위해 BookVO 객체 생성
				BookVO book = new BookVO();
				book.setBook_name(rs.getString("book_name"));
				book.setPrice(rs.getInt("price"));
				book.setBook_img(rs.getString("book_img"));
				book.setStock(rs.getInt("stock"));
				//book.setStatus(rs.getInt("status"));
				
				//ItemVO를 CartVO에 저장
				cart.setBookVO(book);
				
				//동일 상품(item_nuum이 같은 상품)의 총구매 금액 구하기
				cart.setSub_total(cart.getC_quantity() * book.getPrice());
				
				list.add(cart);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//장바구니 상세
	public CartVO getCart(CartVO cart)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartVO cartSaved = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM cart WHERE book_num=? AND mem_num=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cart.getBook_num());
			pstmt.setInt(2, cart.getMem_num());
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) { // 한 건의 데이터 관리
				cartSaved = new CartVO();
				cartSaved.setC_num(rs.getInt("c_num"));
				cartSaved.setBook_num(rs.getInt("book_num"));
				cartSaved.setC_quantity(rs.getInt("c_quantity"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return cartSaved;
	}
	
	//장바구니 수정 (개별 상품 수량 수정)
	public void updateCart(CartVO cart)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {

			conn = DBUtil.getConnection();

			sql = "UPDATE cart SET c_quantity=? WHERE c_num=?";

			pstmt = conn.prepareStatement(sql);
	
			pstmt.setInt(1, cart.getC_quantity());
			pstmt.setInt(2, cart.getC_num());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}DBUtil.executeClose(null, pstmt, conn);
	}
	
	//장바구니 수정 (상품번호와 회원번호별 수정)
	public void updateCartByBook_num(CartVO cart)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "UPDATE cart SET c_quantity=? WHERE book_num=? AND mem_num=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cart.getC_quantity());
			pstmt.setInt(2, cart.getBook_num());
			pstmt.setInt(3, cart.getMem_num());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//장바구니 삭제
	public void deleteCart(int c_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "DELETE FROM cart WHERE c_num=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, c_num);

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}DBUtil.executeClose(null, pstmt, conn);
	}
}
