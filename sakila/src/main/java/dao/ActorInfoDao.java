package dao;
import java.util.*;
import util.DBUtil;
import vo.*;
import java.sql.*;

public class ActorInfoDao {
	//페이징_data200이므로, 페이징작업
	public ArrayList<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage) {
		ArrayList<ActorInfo> list = new ArrayList<ActorInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
	//	DBUtil dbUtil = new DBUtil(); static되며 객체생성 불필요
		conn = DBUtil.getConnection();
		String sql = "select actor_id actorId, first_name firstName, last_name lastName, film_info filmInfo from actor_info order by actor_id limit ?,?";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				ActorInfo a = new ActorInfo();
				a.setActorId(rs.getInt("actorId"));
				a.setFirstName(rs.getString("firstName"));
				a.setLastName(rs.getString("lastName"));
				a.setFilmInfo(rs.getString("filmInfo"));
				list.add(a);
			}
			//rs.next()...list.add
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 전체 행의 수를 반환 메서드
		public int selectactorInfoTotalRow() {
			int row = 0;
			// 데이터베이스 자원 준비
			Connection conn = null;
			String sql = "SELECT COUNT(*) cnt FROM actor_info";
			conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				if(rs.next()) {
				row = rs.getInt("cnt");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					//반납
					conn.close();
					stmt.close();
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}

			}
			return row;
		}
}
