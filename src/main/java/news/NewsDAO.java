package news;

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



public class NewsDAO {
	private Context init;
	private DataSource ds;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static NewsDAO instance = new NewsDAO();
	public static NewsDAO getInstance() {
		return instance;
	}
	
	private NewsDAO() {
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
	
	private NewsDTO mapping(ResultSet rs) throws SQLException {
		NewsDTO dto = new NewsDTO();
		dto.setIdx(rs.getInt("idx"));
		dto.setTitle(rs.getString("title"));
		dto.setWriter(rs.getString("writer"));
		dto.setContent(rs.getString("content"));
		dto.setImage(rs.getString("image"));
		dto.setViewCount(rs.getInt("viewCount"));
		dto.setWriteDate(rs.getDate("writeDate"));
		return dto;
	}
	
	// 게시글 목록
	public List<NewsDTO> selectList(String search, NewsPaging paging) {
		ArrayList<NewsDTO> list = new ArrayList<>();
		String sql = "select * from news "
				+ "		where "
				+ "		 title like '%' || ? || '%' "
				+ "		or "
				+ "		 writer like '%' || ? || '%' "
				+ "		or "
				+ "		 content like '%' || ? || '%' "
				+ "	order by idx desc"
				+ "	offset ? rows"
				+ "	fetch next ? rows only";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			pstmt.setInt(4, paging.getOffset());
			pstmt.setInt(5, paging.getFetch());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return list;
	}
	
	
	// 뉴스 개수 불러오기
	public int selectCount(String search) {
		int count = 0;
		String sql = "select count(*) from news "
				+ "		where "
				+ "		 title like '%' || ? || '%' "
				+ "		or "
				+ "		 writer like '%' || ? || '%' "
				+ "		or "
				+ "		 content like '%' || ? || '%'	";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return count;
	}
	
	
	// 단일 게시글
	
	public NewsDTO selectOne(int idx) {
		NewsDTO dto = null;
		String sql = "select * from news where idx = ? ";
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
	
	// 추가
	public int newsInsert(NewsDTO dto) {
		int row = 0;
		String sql = "insert into news (title, writer, content, image) values ("
				+ "?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getImage());
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { close(); }
		return row;
	}
	
	// 삭제
	public int delete(int idx) {
		int row = 0;
		String sql = "delete news where idx = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println(row);
		return row;
	}
	
	// 조회수
	
	public int updateViewCount(int idx) {
		int row = 0;
		String sql = "update news set viewCount = viewCount + 1 where idx = ?";
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
	
	
	// 뉴스 수정
	public int newsUpdate(NewsDTO dto, int idx) {
		int row = 0;
		String sql = "update news set title = ?, content = ?, image = ? where idx = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImage());
			pstmt.setInt(4, idx);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return row;
	}
	
	
	
	
	
	
	
	
}
