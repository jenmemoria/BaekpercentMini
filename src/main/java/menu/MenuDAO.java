package menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class MenuDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Context init;
	private DataSource ds;
	
	private static MenuDAO instance = new MenuDAO();
	public static MenuDAO getInstance() {
		return instance;
	}
	private MenuDAO() {
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
	
	private MenuDTO mapping(ResultSet rs) throws SQLException {
		MenuDTO dto = new MenuDTO();
		dto.setCategory(rs.getString("category"));
		dto.setIdx(rs.getInt("idx"));
		dto.setMenuImage(rs.getString("menuImage"));
		dto.setMenuName(rs.getString("menuName"));
		return dto;
	}
	
	
	
	
	// 메뉴추가
	public int insert(MenuDTO dto) {
		int row = 0;
		String sql = " insert into menu (menuName, menuImage, category) values (? , ? , ?) ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMenuName());
			pstmt.setString(2, dto.getMenuImage());
			pstmt.setString(3, dto.getCategory());
			row  = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return row;
	}
	
	
	
	// 메뉴삭제
	public int delete(int idx) {
		int row = 0;
		String sql = " delete menu where idx = ?  ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return row;
	}
	
	// 메뉴내용 수정
	public int update(MenuDTO dto) {
		int row = 0;
		String sql = "update menu set menuName = ?, menuImage = ?, category = ? where idx = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMenuName());
			pstmt.setString(2, dto.getMenuImage());
			pstmt.setString(3, dto.getCategory());
			pstmt.setInt(4, dto.getIdx());
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return row;
	}
	
	// 신제품
	
	public List<MenuDTO> newArrivedList(String search, MenuPaging paging) {
		ArrayList<MenuDTO> list = new ArrayList<>();
		String sql = " select * from menu where category = '신메뉴' "
								+ "	and "
								+ "	(menuName like '%' || ? || '%' ) "
								+ " order by idx desc "
								+ " offset ? rows "
								+ " fetch next ? rows only ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, paging.getOffset());
			pstmt.setInt(3, paging.getFetch());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return list;
	}
	
	
	
	
	public List<MenuDTO> coffeeList(String search, MenuPaging paging) {
		ArrayList<MenuDTO> list = new ArrayList<>();
		String sql = " select * from menu where category = '커피' "
								+ "	and "
								+ "	(menuName like '%' || ? || '%' ) "
								+ " order by idx desc "
								+ " offset ? rows "
								+ " fetch next ? rows only ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, paging.getOffset());
			pstmt.setInt(3, paging.getFetch());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return list;
	}
		
	public List<MenuDTO> drinkList(String search, MenuPaging paging) {
		ArrayList<MenuDTO> list = new ArrayList<>();
		String sql = " select * from menu where category = '음료/티' "
								+ "	and "
								+ "	(menuName like '%' || ? || '%' ) "
								+ " order by idx desc "
								+ " offset ? rows "
								+ " fetch next ? rows only ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, paging.getOffset());
			pstmt.setInt(3, paging.getFetch());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return list;
	}
	
	public List<MenuDTO> dessertList(String search, MenuPaging paging) {
		ArrayList<MenuDTO> list = new ArrayList<>();
		String sql = " select * from menu where category = '디저트' "
								+ "	and "
								+ "	(menuName like '%' || ? || '%' ) "
								+ " order by idx desc "
								+ " offset ? rows "
								+ " fetch next ? rows only ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, paging.getOffset());
			pstmt.setInt(3, paging.getFetch());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return list;
	}
	
	
	public List<MenuDTO> mdList(String search, MenuPaging paging) {
		ArrayList<MenuDTO> list = new ArrayList<>();
		String sql = " select * from menu where category = 'MD' "
								+ "	and "
								+ "	(menuName like '%' || ? || '%' ) "
								+ " order by idx desc "
								+ " offset ? rows "
								+ " fetch next ? rows only ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, paging.getOffset());
			pstmt.setInt(3, paging.getFetch());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return list;
	}
	
	
	
	
	public MenuDTO selectOne(int idx) {
		MenuDTO dto = null;
		String sql = " select * from menu where idx = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = mapping(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return dto;
	}
	
	
	public int newArrivedCount(String search) {
		int count = 0;
		String sql = "select count(*) from menu "
				+ "	where category='신메뉴' and "
				+ "		 (menuName like '%' || ? || '%' or "
				+ "		 menuImage like '%' || ? || '%') ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return count;
	}
	
	
	
	public int coffeeCount(String search) {
		int count = 0;
		String sql = "select count(*) from menu "
				+ "	where category='커피' and "
				+ "		 (menuName like '%' || ? || '%' or "
				+ "		 menuImage like '%' || ? || '%') ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return count;
	}
	
	
	
	public int drinkCount(String search) {
		int count = 0;
		String sql = "select count(*) from menu "
				+ "	where category='음료/티' and "
				+ "		 (menuName like '%' || ? || '%' or "
				+ "		 menuImage like '%' || ? || '%') ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return count;
	}
	
	
	
	public int dessertCount(String search) {
		int count = 0;
		String sql = "select count(*) from menu "
				+ "	where category='디저트' and "
				+ "		 (menuName like '%' || ? || '%' or "
				+ "		 menuImage like '%' || ? || '%') ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return count;
	}
	
	public int mdCount(String search) {
		int count = 0;
		String sql = "select count(*) from menu "
				+ "	where category='MD' and "
				+ "		 (menuName like '%' || ? || '%' or "
				+ "		 menuImage like '%' || ? || '%') ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return count;
	}
	
	
	
	
	
}
