package dao;

import java.util.*;
import java.sql.*;

public class StaffDao {
	public List<Map<String,Object>> selectStaffList(){
		List<Map<String,Object>> list = new ArrayList<>(); //다형성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			String sql = "SELECT"
					+ "		t.staff_id staffId,"
					+ "		s.store_id storeId,"
					+ "		concat(t.first_name,' ',t.last_name) staffName,"
					+ "		t.address_id addressId,"
					+ "		concat(a.address, IFNULL(a.address2,' '), district) staffAddress,"
					+ "		t.email email,"
					+ "		t.username username,"	
					+ "		t.last_update lastUpdate"
					+ " FROM staff t"
					+ " INNER JOIN store s"
					+ " INNER JOIN address a"
					+ " ON t.store_id = s.store_id"
					+ " AND t.address_id = a.address_id;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("staffId", rs.getInt("staffId"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("addressId", rs.getInt("addressId"));
				map.put("staffAddress", rs.getString("staffAddress"));
				map.put("email", rs.getString("email"));
				map.put("username", rs.getString("username"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
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

	public static void main(String[] args) {
		StaffDao dao = new StaffDao();
		List<Map<String, Object>> list = dao.selectStaffList();
			for(Map m : list) {
			System.out.print(m.get("staffId")+", ");
			System.out.print(m.get("storeId")+", ");
			System.out.print(m.get("staffName")+", ");
			System.out.println(m.get("addressId")+",");
			System.out.print(m.get("staffAddress")+", ");
			System.out.print(m.get("email")+", ");			
			System.out.print(m.get("username")+", ");
			System.out.print(m.get("lastUpdate"));
			System.out.println("");
		}
	}
}
