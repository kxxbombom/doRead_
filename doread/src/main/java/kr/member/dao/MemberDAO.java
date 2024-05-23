package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	//회원가입(등록)
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0;
		try {
			conn = DBUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			//회원번호 생성
			sql = "SELECT member_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			sql = "INSERT INTO member (mem_num, mem_id) VALUES (?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, member.getMem_id());
			pstmt2.executeUpdate();
			
			sql = "INSERT INTO member_detail (mem_num, mem_name, mem_pw, mem_phone, mem_email, mem_zipcode, mem_address1, mem_address2) VALUES (?,?,?,?,?,?,?,?)";
			
			pstmt3 = conn.prepareStatement(sql);
			
			pstmt3.setInt(1, num);
			pstmt3.setString(2, member.getMem_name());
			pstmt3.setString(3, member.getMem_pw());
			pstmt3.setString(4, member.getMem_phone());
			pstmt3.setString(5, member.getMem_email());
			pstmt3.setString(6, member.getMem_zipcode());
			pstmt3.setString(7, member.getMem_address1());
			pstmt3.setString(8, member.getMem_address2());
			
			conn.commit();
			
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//아이디중복체크, 로그인
	public MemberVO checkMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		MemberVO member = null;
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM member LEFT OUTER JOIN member_detail USING(mem_num) WHERE mem_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_auth(rs.getInt("mem_auth"));
				member.setMem_pw(rs.getString("mem_pw"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_phone(rs.getString("mem_phone"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	//회원정보수정, 비밀번호 변경, 비밀번호 수정, 프로필 사진 수정, 회원 탈퇴(삭제)
}
