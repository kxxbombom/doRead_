package kr.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
import kr.util.DBUtil;

public class OrderDAO {
	private static OrderDAO instance = new OrderDAO();
		
	public static OrderDAO getInstance() {
		return instance;
	}
	private OrderDAO() {}
	
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
			sql = "INSERT INTO book_order (order_num,order_total,order_name,order_zipcode,order_address1,"
					+ "order_address2,order_phone,order_msg,order_payment,mem_num"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
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
			sql = "INSERT INTO book_order_detail (detail_num,book_price,book_total,order_quantity,order_num,book_num,book_name) "
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
	//관리자 - 전체/검색 주문 개수
	//관리자 - 전체/검색 주문 목록
	//사용자 - 전체/검색 주문 개수
	//사용자 - 전체/검색 주문 목록
	//개별 도서 목록
	//주문삭제(주문 내역만 삭제. 재고 원상 복귀X)
	//관리자/사용자 - 주문상세
	
	//관리자/사용자 - 배송지정보 수정
	//관리자 - 배송상태 수정
	//사용자 - 주문 취소
	
}
