package kr.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
import kr.order.vo.PointVO;
import kr.util.DBUtil;

public class OrdersDAO {
	private static OrdersDAO instance = new OrdersDAO();
		
	public static OrdersDAO getInstance() {
		return instance;
	}
	private OrdersDAO() {}
	public void insertOrder(OrderVO order, List<OrderDetailVO> orderDetailList)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
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
			
			if(order.getOrder_usepoint() !=0) {
			sql="insert into point(p_num,p_detail,p_point,mem_num) values(point_seq.nextval,?,?,?)";
			pstmt6 = conn.prepareStatement(sql);
			pstmt6.setInt(1, 1);
			pstmt6.setInt(2, order.getOrder_usepoint());
			pstmt6.setInt(3,order.getMem_num());
			pstmt6.executeUpdate();
				
			}
			
			sql="insert into point(p_num,p_detail,p_point,mem_num) values(point_seq.nextval,?,?,?)";
			pstmt7 = conn.prepareStatement(sql);
			pstmt7.setInt(1, 0);
			pstmt7.setInt(2, (int)(order.getOrder_total()*0.03));
			pstmt7.setInt(3,order.getMem_num());
			pstmt7.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt7, null);
			if(order.getOrder_usepoint() !=0) {
			DBUtil.executeClose(null, pstmt6, null);
			}
			DBUtil.executeClose(null, pstmt5, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	//포인트정보 (총합계)
	public int getPoint(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql = null;
		int point = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT sum(p_point) FROM point WHERE mem_num=? and p_detail=0";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
				point += rs.getInt(1);
				}while(rs.next());
				}
			sql = "SELECT sum(p_point) FROM point WHERE mem_num=? and p_detail=1";
			
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, mem_num);
			
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) {
			do {
				point -= rs2.getInt(1);
			}while(rs2.next());
					
			}
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs2, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return point;
	}
	//point 사용 내역 갯수 페이지 처리
	public int getPagePoint(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT count(*) FROM point WHERE mem_num=? ";
			
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
	//포인트 사용내역
	public List<PointVO> getListUserPoint(int start, int end, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PointVO> list=null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql="select * from (select rownum alnum, a.* from (select * from point where mem_num=? order by p_num desc)a) where alnum between ? and ?";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<PointVO>();
				do {
					PointVO point = new PointVO();
					point.setP_num(rs.getInt("p_num"));
					point.setP_detail(rs.getInt("p_detail"));
					point.setP_point(rs.getInt("p_point"));
					point.setMem_num(rs.getInt("mem_num"));
					point.setP_rdate(rs.getDate("p_rdate"));
					list.add(point);
				}while(rs.next());
				
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
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
