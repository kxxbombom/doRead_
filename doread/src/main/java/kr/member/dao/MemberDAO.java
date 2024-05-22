package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	//회원가입(등록), 아이디중복체크, 회원정보수정, 비밀번호 변경, 비밀번호 수정, 프로필 사진 수정, 회원 탈퇴(삭제)
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			//회원번호 생성
			//member insert
			//member_detail insert
			
			
			
			
			sql = "INSERT INTO member_detail (mem_num, name, passwd, phone, email, zipcode, address1, address2, photo, reg_date, modify_date) VALUES (member_seq.nextval,?,?,?,?,?,?,?,?,SYSDATE,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sql);
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getPhone());
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
