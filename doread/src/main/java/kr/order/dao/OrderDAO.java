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

public class OrderDAO {
	private static OrderDAO instance = new OrderDAO();
		
	public static OrderDAO getInstance() {
		return instance;
	}
	private OrderDAO() {}
	
	//주문등록
	public void insertOrder(OrderVO order, List<OrderDetailVO> orderDetailList, int one)throws Exception{
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
					+ "receive_address2, receive_phone, order_msg, order_payment, mem_num, enter, enter_passwd) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
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
			pstmt2.setInt(11, order.getEnter());
			if(order.getEnter_passwd() != null) {
				pstmt2.setString(12, order.getEnter_passwd());
			}else {
				pstmt2.setNull(12, java.sql.Types.VARCHAR);
			}
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
			if(one != 1) {
			sql = "DELETE FROM cart WHERE mem_num=?";
			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setInt(1, order.getMem_num());
			pstmt5.executeUpdate();
			}
			
			
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
			pstmt7.setInt(2, (int)Math.floor(order.getAll_total()*0.03));
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
			if(one != 1) {
			DBUtil.executeClose(null, pstmt5, null);
			}
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	//포인트정보
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
					sql = "SELECT sum(p_point) FROM point WHERE mem_num=? and p_detail=1 or p_detail=2";
					
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
	public int getAdminOrderCount(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		int cnt =0;
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1"))sub_sql += " where  order_num=? " ;
				else if(keyfield.equals("2"))sub_sql += " where mem_id like '%' || ? || '%' ";
				else if(keyfield.equals("3"))sub_sql += " where book_name like '%' || ? || '%' ";

			}

			sql = "SELECT COUNT(*) FROM book_order JOIN (SELECT order_num, LISTAGG(book_name,',') WITHIN GROUP (ORDER BY book_name) book_name "
					+ "FROM book_order_detail GROUP BY order_num) USING (order_num) join member using(mem_num) " + sub_sql;

			pstmt = conn.prepareStatement(sql);


			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
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
	//관리자 - 전체/검색 주문 목록
	public List<OrderVO> getAdminListOrderByMem_num(int start, int end, String keyfield, String keyword)throws Exception{
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
				if(keyfield.equals("1"))sub_sql += " where  order_num=? " ;
				else if(keyfield.equals("2"))sub_sql += " where mem_id like '%' || ? || '%' ";
				else if(keyfield.equals("3"))sub_sql += " where book_name like '%' || ? || '%' ";

			}

			

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM ("
					+ "SELECT * FROM book_order JOIN (SELECT order_num, LISTAGG(book_name,',') "
					+ "WITHIN GROUP (ORDER BY book_name) book_name FROM book_order_detail GROUP BY order_num) "
					+ "USING (order_num) join member using(mem_num)  " + sub_sql
					+ " ORDER BY order_num DESC)a) WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);
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
				order.setMem_num(rs.getInt("mem_num"));
				order.setMem_id(rs.getString("mem_id"));
				
				list.add(order);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
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
				else if(keyfield.equals("2")) sub_sql += "AND order_num=?";
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
				else if(keyfield.equals("2")) sub_sql += "AND order_num=?";
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
	//개별상품 목록
		public List<OrderDetailVO> getListOrder_Detail(int order_num) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet re= null;
			String sql= null;
			List<OrderDetailVO> list = null;
			try {
				conn = DBUtil.getConnection();
				sql="select * from book_order_detail join book using(book_num) where order_num=? order by book_num desc ";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, order_num);
				re=ps.executeQuery();
				if(re.next()) {
					list = new ArrayList<OrderDetailVO>();
					do {
						OrderDetailVO detail = new OrderDetailVO();
						detail.setDetail_num(re.getInt("detail_num"));
						detail.setBook_num(re.getInt("book_num"));
						detail.setBook_name(re.getString("book_name"));
						detail.setBook_price(re.getInt("book_price"));
						detail.setBook_total(re.getInt("Book_total"));
						detail.setOrder_num(re.getInt("order_num"));
						detail.setOrder_quantity(re.getInt("order_quantity"));
						detail.setBook_image(re.getString("book_img"));
						list.add(detail);
					}while(re.next());
					
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally{
				DBUtil.executeClose(re, ps, conn);
			}
			
			return list;
		}
	
	//주문 삭제(삭제시 재고를 원상복귀 시키지 않음.) 주문취소일때 재고수량 원상복귀
		public void deleteUserorder(int order_num ) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			PreparedStatement ps2 = null;
			String sql= null;
			
			try {
				conn =DBUtil.getConnection();
				conn.setAutoCommit(false);
				sql="delete from book_order where order_num =?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, order_num);
				
				ps.executeUpdate();
				sql="delete from book_order_detail where order_num=? ";
				ps2 = conn.prepareStatement(sql);
				ps2.setInt(1, order_num);
				ps2.executeUpdate();
				
				conn.commit();
				
			}catch(Exception e){
				conn.rollback();
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, ps, conn);
			}
			
			
		}
	//관리자/사용자 - 주문상세
	public OrderVO getBookOrdervo(int order_num) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet re= null;
		String sql= null;
		OrderVO order = null;
		try {
			conn = DBUtil.getConnection();
			sql="select * from book_order where order_num=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, order_num);
			re = ps.executeQuery();
			if(re.next()) {
				order = new OrderVO();
				
				order.setOrder_num(re.getInt("order_num"));
				order.setOrder_total(re.getInt("order_total"));
				order.setOrder_status(re.getInt("order_status"));
				order.setOrder_payment(re.getInt("order_payment"));
				order.setMem_num(re.getInt("Mem_num"));
				order.setReceive_name(re.getString("receive_name"));
				order.setReceive_zipcode(re.getString("receive_zipcode"));
				order.setReceive_address1(re.getString("receive_address1"));
				order.setReceive_address2(re.getString("receive_address2"));
				order.setReceive_phone(re.getString("receive_phone"));
				order.setOrder_msg(re.getString("order_msg"));
				order.setOrder_date(re.getDate("order_date"));
				order.setOrder_mdate(re.getDate("order_mdate"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(re, ps, conn);
		}
		
		return order;
	}
		
	
	//관리자 / 사용자 - 배송지 정보 수정
		public void updateOrder(OrderVO order) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			String sql= null;
			try {
				conn = DBUtil.getConnection();
				sql="update book_order set receive_name=?,receive_zipcode=?,receive_address1=?,receive_address2=?,receive_phone=?,order_msg=?,ORDER_MDATE=sysdate where order_num=?";
				ps =conn.prepareStatement(sql);
				ps.setString(1, order.getReceive_name());
				ps.setString(2, order.getReceive_zipcode());
				ps.setString(3, order.getReceive_address1());
				ps.setString(4, order.getReceive_address2());
				ps.setString(5, order.getReceive_phone());
				ps.setString(6, order.getOrder_msg());
				ps.setInt(7, order.getOrder_num());
				
				ps.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, ps, conn);
			}
			
			
		}
		//관리자 배송상태 수정
		public void updatestatus(OrderVO order) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			PreparedStatement ps2 = null;
			PreparedStatement ps4 = null;
			String sql= null;
			try {
				conn = DBUtil.getConnection();
				conn.setAutoCommit(false);
				sql="update book_order set order_status=?, ORDER_MDATE=sysdate  where order_num=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, order.getOrder_status());
				ps.setInt(2, order.getOrder_num());
				ps.executeUpdate();
				if(order.getOrder_status() == 5) {
					sql="insert into point(p_num,p_detail,p_point,mem_num) values(point_seq.nextval,?,?,?)";
					ps4 = conn.prepareStatement(sql);
					ps4.setInt(1, 2);
					ps4.setInt(2,(int)Math.floor(order.getAll_total()*0.03));
					ps4.setInt(3,order.getMem_num());
					ps4.executeUpdate();
					
					List<OrderDetailVO> detail = getListOrder_Detail(order.getOrder_num());
					sql="update book set stock=stock+?, ORDER_MDATE=sysdate  where book_num=?";
					ps2 = conn.prepareStatement(sql);
					for(int i=0; i<detail.size(); i++) {
						ps2.setInt(1, detail.get(i).getOrder_quantity());
						ps2.setInt(2, detail.get(i).getBook_num());
						ps2.addBatch();
						if(i%1000==0) {
							ps2.executeBatch();
						}
					}
					
					ps2.executeBatch();
				}
				conn.commit();
			}catch(Exception e) {
				conn.rollback();
				throw new Exception(e);
			}finally {
				if(order.getOrder_status() == 5) {
				DBUtil.executeClose(null, ps2, null);
				}
				DBUtil.executeClose(null, ps, conn);
			}
			
			
		}

			//사용자 - 주문취소
		public void cancleOrderuser(OrderVO order) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			PreparedStatement ps3 = null;
			PreparedStatement ps4 = null;
			String sql= null;
			
			try {
				conn =DBUtil.getConnection();
				conn.setAutoCommit(false);
				sql="update book_order set order_status=5, ORDER_MDATE=sysdate where order_num=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, order.getOrder_num());
				ps.executeUpdate();
				
				sql="insert into point(p_num,p_detail,p_point,mem_num) values(point_seq.nextval,?,?,?)";
				ps4 = conn.prepareStatement(sql);
				ps4.setInt(1, 2);
				ps4.setInt(2,(int)Math.floor(order.getAll_total()*0.03));
				ps4.setInt(3,order.getMem_num());
				ps4.executeUpdate();
				
				//주문취소했기때문에 환원이여
				List<OrderDetailVO> list = getListOrder_Detail(order.getOrder_num());
				sql="update zitem set quantity=quantity+? where book_num=? ";
				ps3 = conn.prepareStatement(sql);
				for(int i=0; i<list.size(); i++) {
					ps3.setInt(1, list.get(i).getOrder_quantity());
					ps3.setInt(2, list.get(i).getBook_num());
					ps3.addBatch();
					if(i%1000 == 0) {
						ps3.executeBatch();
					}
					
				}
				ps3.executeBatch();
				
				conn.commit();
				
			}catch(Exception e){
				conn.rollback();
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, ps4, null);
				DBUtil.executeClose(null, ps3, null);
		
				DBUtil.executeClose(null, ps, conn);
			}
			
			
		}
			
		}
