package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Context init;
	private DataSource ds;
	
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {
		try {
			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private void close() {
		try {
			if(rs != null) 		rs.close();
			if(pstmt != null) 	pstmt.close();
			if(conn != null) 	conn.close();
		}catch(SQLException e) {}
	}
	
	private MemberDTO mapping(ResultSet rs) throws SQLException {
		MemberDTO dto = new MemberDTO();
		dto.setEmail(rs.getString("email"));
		dto.setIdx(rs.getInt("idx"));
		dto.setUserid(rs.getString("userid"));
		dto.setUsername(rs.getString("username"));
		dto.setUserpw(rs.getString("userpw"));
		return dto;
	}
	
	// 회원 가입
		public int insert(MemberDTO dto) {
			int row = 0;
			String sql = "insert into member (userid, userpw, username, email) "
					+ "values (?, ?, ?, ?)";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUserid());
				pstmt.setString(2, dto.getUserpw());
				pstmt.setString(3, dto.getUsername());
				pstmt.setString(4, dto.getEmail());
				row = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { close(); }
			return row;
		}
	
		// 로그인
		public MemberDTO login(MemberDTO dto) {
			MemberDTO login = null;
			String sql = "select * from member where userid = ? and userpw = ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUserid());
				pstmt.setString(2, dto.getUserpw());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					login = mapping(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { close(); }
			return login;
		}
		
		// 회원 탈퇴
		public int delete(String userid, String userpw) {
			int row = 0;
			String sql = "delete from member where userid = ? and userpw = ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, userpw);
				row = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { close(); }
			System.out.println(row);
			return row;
		}
		
		
		// 아이디 중복 체크
	      public int duplecateID(String userid){
	         int cnt=0;
	         String sql = "select count(userid) as cnt from member where userid = ?";
	          try{
	             conn = ds.getConnection();
	             pstmt = conn.prepareStatement(sql);
	             pstmt.setString(1, userid);
	             rs = pstmt.executeQuery();
	              while(rs.next()) {
	                 cnt = rs.getInt("cnt");
	              }
	          }catch(Exception e){
	              System.out.println("아이디 중복 확인 실패 : " + e);
	          }//try end
	         return cnt;
	      }//duplecateID end
}
