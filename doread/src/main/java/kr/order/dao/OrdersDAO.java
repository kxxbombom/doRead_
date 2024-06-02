package kr.order.dao;

public class OrdersDAO {
	private static OrdersDAO instance = new OrdersDAO();
		
	public static OrdersDAO getInstance() {
		return instance;
	}
	private OrdersDAO() {}
	
	//주문등록
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
