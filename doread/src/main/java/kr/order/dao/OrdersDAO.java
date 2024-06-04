package kr.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
import kr.util.DBUtil;

public class OrdersDAO {
	private static OrdersDAO instance = new OrdersDAO();
		
	public static OrdersDAO getInstance() {
		return instance;
	}
	private OrdersDAO() {}
	
	//주문등록
	public void insertOrder(OrderVO order, List<OrderDetailVO> orderDetailList)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs = null;
		String sql = null;
		int order_num = 0; //시퀀스
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			//주문번호
			sql = "SELECT order_seq.nextval FROM dual";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				order_num = rs.getInt(1);
			}
			
			//주문정보 처리
			sql = "INSERT INTO book_order (order_num, order_total, receive_name, receive_zipcode, receive_address1,"
					+ "receive_address2, receive_phone, order_msg, order_payment, mem_num) VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, order_num);
			pstmt2.setInt(2, order.getOrder_total());
			pstmt2.setString(3, order.getReceive_name());
			pstmt2.setString(4, order.getReceive_zipcode());
			pstmt2.setString(5, order.getReceive_address1());
			pstmt2.setString(6, order.getReceive_address2());
			pstmt2.setString(7, order.getReceive_phone());
			pstmt2.setString(8, order.getOrder_msg());
			pstmt2.setInt(9, order.getOrder_payment());
			pstmt2.setInt(10, order.getMem_num());
			pstmt2.executeUpdate();
			
			//주문상세정보
			sql = "INSERT INTO book_order_detail (detail_num, book_price, book_total, order_quantity, order_num, book_num, book_name) "
					+ "VALUES (order_detail_seq.nextval,?,?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			
			for(int i=0;i<orderDetailList.size();i++) {
				OrderDetailVO orderDetail = orderDetailList.get(i);
				pstmt3.setInt(1, orderDetail.getBook_price());
				pstmt3.setInt(2, orderDetail.getBook_total());
				pstmt3.setInt(3, orderDetail.getOrder_quantity());
				pstmt3.setInt(4, order_num);
				pstmt3.setInt(5, orderDetail.getBook_num());
				pstmt3.setString(6, orderDetail.getBook_name());
				
				pstmt3.addBatch();
				
				if(i % 1000 == 0) {
					pstmt3.executeBatch();
				}
			}
			pstmt3.executeBatch();
			
			//도서 재고 차감
			sql = "UPDATE book SET stock=stock-? WHERE book_num=?";
			pstmt4 = conn.prepareStatement(sql);
			for(int i=0;i<orderDetailList.size();i++) {
				OrderDetailVO orderDetail = orderDetailList.get(i);
				pstmt4.setInt(1, orderDetail.getOrder_quantity());
				pstmt4.setInt(2, orderDetail.getBook_num());
				pstmt4.addBatch();
				
				if(i % 1000 == 0) {
					pstmt4.executeBatch();
				}
			}
			pstmt4.executeBatch();
			
			//장바구니에서 삭제
			sql = "DELETE FROM cart WHERE mem_num=?";
			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setInt(1, order.getMem_num());
			pstmt5.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt5, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	//포인트정보
	public int getPoint(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int point = 0;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT p_point FROM point WHERE mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return point;
	}
	//관리자 - 전체/검색 주문 개수
	//관리자 - 전체/검색 주문 목록
	//사용자 - 전체/검색 주문 개수
	public int getOrderCount(String keyfield, String keyword, int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "AND book_name LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "AND order_num LIKE '%' || ? || '%'";
			}

			sql = "SELECT COUNT(*) FROM book_order JOIN (SELECT order_num, LISTAGG(book_name,',') WITHIN GROUP (ORDER BY book_name) book_name "
					+ "FROM book_order_detail GROUP BY order_num) USING (order_num) WHERE mem_num=? " + sub_sql;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mem_num);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(2, keyword);
			}
			//SQL문 실행
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
	//사용자 - 전체/검색 주문 목록
	public List<OrderVO> getListOrderByMem_num(int start, int end, String keyfield, String keyword, int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "AND book_name LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "AND order_num LIKE '%' || ? || '%'";
			}
			

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM ("
					+ "SELECT * FROM book_order JOIN (SELECT order_num, LISTAGG(book_name,',') "
					+ "WITHIN GROUP (ORDER BY book_name) book_name FROM book_order_detail GROUP BY order_num) "
					+ "USING (order_num) WHERE mem_num=? " + sub_sql
					+ " ORDER BY order_num DESC)a) WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(++cnt, mem_num);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<OrderVO>();
			while(rs.next()) {
				OrderVO order = new OrderVO();
				order.setOrder_num(rs.getInt("order_num"));
				order.setOrder_total(rs.getInt("order_total"));
				order.setBook_name(rs.getString("book_name"));
				order.setOrder_status(rs.getInt("order_status"));
				order.setOrder_date(rs.getDate("order_date"));
				
				list.add(order);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//개별 도서 목록
	public List<OrderDetailVO> getListOrderDetail(int order_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderDetailVO> list = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM book_order_detail JOIN book USING (book_num) WHERE order_num=? ORDER BY book_num DESC";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, order_num);

			rs = pstmt.executeQuery();
			
			list = new ArrayList<OrderDetailVO>();
			while(rs.next()) {
				OrderDetailVO detail = new OrderDetailVO();
				detail.setBook_num(rs.getInt("book_num"));
				detail.setBook_name(rs.getString("book_name"));
				detail.setBook_price(rs.getInt("book_price"));
				detail.setBook_total(rs.getInt("book_total"));
				detail.setOrder_quantity(rs.getInt("order_quantity"));
				detail.setOrder_num(rs.getInt("order_num"));
				detail.setBook_image(rs.getString("book_img"));
				list.add(detail);
			}	
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//주문삭제(주문 내역만 삭제. 재고 원상 복귀X)
	//관리자/사용자 - 주문상세
	public OrderVO getOrder(int order_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO order = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM book_order WHERE order_num=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, order_num);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				order = new OrderVO();
				order.setOrder_num(rs.getInt("order_num"));
				order.setOrder_total(rs.getInt("order_total"));
				order.setOrder_payment(rs.getInt("order_payment"));
				order.setOrder_status(rs.getInt("order_status"));
				order.setReceive_name(rs.getString("receive_name"));
				order.setReceive_zipcode(rs.getString("receive_zipcode"));
				order.setReceive_address1(rs.getString("receive_address1"));
				order.setReceive_address2(rs.getString("receive_address2"));
				order.setReceive_phone(rs.getString("receive_phone"));
				order.setOrder_date(rs.getDate("order_date"));
				order.setOrder_mdate(rs.getDate("order_mdate"));
				order.setMem_num(rs.getInt("mem_num"));
				order.setOrder_msg(rs.getString("order_msg"));
				order.setEnter(rs.getInt("enter"));
				order.setEnter_passwd(rs.getString("enter_passwd"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return order;
	}
	//관리자/사용자 - 배송지정보 수정
	public void updateOrder(OrderVO order, int order_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "UPDATE book_order SET receive_name=?, receive_phone=?, receive_zipcode=?, receive_address1=?, receive_address2=?, order_msg=?, enter=?, enter_passwd=? WHERE order_num=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, order.getReceive_name());
			pstmt.setString(2, order.getReceive_phone());
			pstmt.setString(3, order.getReceive_zipcode());
			pstmt.setString(4, order.getReceive_address1());
			pstmt.setString(5, order.getReceive_address2());
			if(order.getOrder_msg() == null) {
				pstmt.setNull(6, java.sql.Types.VARCHAR);
			}else {
				pstmt.setString(6, order.getOrder_msg());
			}
			pstmt.setInt(7, order.getEnter());
			if (order.getEnter_passwd() == null) {
			    pstmt.setNull(8, java.sql.Types.VARCHAR);
			} else {
			    pstmt.setString(8, order.getEnter_passwd());
			}
			pstmt.setInt(9, order_num);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//관리자 - 배송상태 수정
	//사용자 - 주문 취소
	public void cancelOrderUser(int order_num) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		String sql= null;
		
		try {
			conn =DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			sql="UPDATE book_order set order_status=5, order_mdate=sysdate WHERE order_num=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, order_num);
			ps.executeUpdate();
			
			//주문취소 재고 환원
			List<OrderDetailVO> list = getListOrderDetail(order_num);
			sql="UPDATE book set stock=stock+? WHERE book_num=? ";
			ps2 = conn.prepareStatement(sql);
			for(int i=0; i<list.size(); i++) {
				ps2.setInt(1, list.get(i).getOrder_quantity());
				ps2.setInt(2, list.get(i).getBook_num());
				ps2.addBatch();
				if(i%1000 == 0) {
					ps2.executeBatch();
				}
				
			}
			ps2.executeBatch();
			
			conn.commit();
			
		}catch(Exception e){
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, ps2, null);
			DBUtil.executeClose(null, ps, conn);
		}
		
		
	}
	
}
